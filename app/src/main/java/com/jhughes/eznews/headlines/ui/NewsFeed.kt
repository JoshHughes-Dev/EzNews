package com.jhughes.eznews.headlines.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.headlines.HeadlinesViewModel
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun NewsFeed(
    modifier: Modifier = Modifier,
    headlinesViewModel: HeadlinesViewModel,
    headerItem: @Composable () -> Unit = {}
) {

    val lazyPagingItems: LazyPagingItems<Article> =
        headlinesViewModel.topHeadlines.collectAsLazyPagingItems()

    val lazyListState = rememberLazyListState()

    Box(modifier = modifier) {
        LazyColumn(
            state = lazyListState,
        ) {

            item {
                headerItem()
            }

            when (val refreshState = lazyPagingItems.loadState.refresh) {
                is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            alignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                is LoadState.Error -> {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            alignment = Alignment.Center,
                        ) {
                            Text(text = "Full Error: ${refreshState.error.toString()}")
                        }
                    }
                }
            }

            if (lazyPagingItems.loadState.refresh is LoadState.NotLoading) {
                itemsIndexed(lazyPagingItems) { index, item ->
                    if (item != null) {
                        val top = if (index == 0) 8.dp else 0.dp
                        ArticleItem(
                            modifier = Modifier.padding(
                                start = 20.dp,
                                top = top,
                                end = 20.dp,
                                bottom = 20.dp
                            ),
                            article = item,
                            onClick = {})
                    }
                }
            }

            when (val appendState = lazyPagingItems.loadState.append) {
                is LoadState.Loading -> {
                    item {
                        Box(alignment = Alignment.Center) {
                            Text(
                                modifier = Modifier.padding(vertical = 16.dp, horizontal = 4.dp),
                                text = "Loading next"
                            )
                        }
                    }
                }
                is LoadState.Error -> {
                    item {
                        Box(alignment = Alignment.Center) {
                            Text(
                                modifier = Modifier.padding(vertical = 16.dp, horizontal = 4.dp),
                                text = "Error getting next: ${appendState.error.toString()}"
                            )
                        }
                    }
                }
            }
        }

        //Threshold for button showing
        val jumpToTopButtonEnabled = lazyListState.firstVisibleItemIndex > 2

        JumpToTopButton(
            // Only show if the scroller is not at the top
            enabled = jumpToTopButtonEnabled,
            onClicked = {
                //todo
            },
            modifier = Modifier.align(Alignment.BottomCenter).navigationBarsPadding()
        )
    }
}