package com.jhughes.eznews.headlines.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    headerItem : @Composable () -> Unit = {}) {

    val lazyPagingItems: LazyPagingItems<Article> =
        headlinesViewModel.topHeadlines.collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 8.dp)
    ) {

        item {
            headerItem()
        }

        when (val refreshState = lazyPagingItems.loadState.refresh) {
            is LoadState.Loading -> {
                item {
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 4.dp),
                        text = "Full Loading"
                    )
                }
            }
            is LoadState.Error -> {
                item {
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 4.dp),
                        text = "Full Error: ${refreshState.error.toString()}"
                    )
                }
            }
        }

        if (lazyPagingItems.loadState.refresh is LoadState.NotLoading) {
            itemsIndexed(lazyPagingItems) { index, item ->
                if (item != null) {
                    ArticleItem(
                        modifier = Modifier.padding(bottom = 20.dp),
                        article = item,
                        onClick = {})
                }
            }
        }

        when (val appendState = lazyPagingItems.loadState.append) {
            is LoadState.Loading -> {
                item {
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 4.dp),
                        text = "Loading next"
                    )
                }
            }
            is LoadState.Error -> {
                item {
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 4.dp),
                        text = "Error getting next: ${appendState.error.toString()}"
                    )
                }
            }
        }
    }
}