package com.jhughes.eznews.headlines.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import androidx.ui.tooling.preview.Preview
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.headlines.HeadlinesViewModel
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun NewsFeed(
    modifier: Modifier = Modifier,
    headlinesViewModel: HeadlinesViewModel,
    headerItem: @Composable () -> Unit = {},
    onSelectArticle: (Article) -> Unit = {}
) {
    val lazyPagingItems: LazyPagingItems<Article> =
        headlinesViewModel.topHeadlines.collectAsLazyPagingItems()

    val feedListState = rememberLazyListState()

    Box(modifier = modifier) {
        LazyColumn(state = feedListState) {
            item {
                headerItem()
            }

            when (val refreshState = lazyPagingItems.loadState.refresh) {
                is LoadState.Loading -> {
                    item { NewsFeedLoading() }
                }
                is LoadState.Error -> {
                    item {
                        NewsFeedError(error = refreshState.error) {
                            lazyPagingItems.retry()
                        }
                    }
                }
            }

            if (lazyPagingItems.loadState.refresh is LoadState.NotLoading) {
                itemsIndexed(lazyPagingItems) { index, item ->
                    NewsFeedItem(index, item, onItemSelected = onSelectArticle)
                }
            }

            when (val appendState = lazyPagingItems.loadState.append) {
                is LoadState.Loading -> {
                    item { NewsFeedPageLoading() }
                }
                is LoadState.Error -> {
                    item {
                        NewsFeedPageError(error = appendState.error) {
                            lazyPagingItems.retry()
                        }
                    }
                }
            }
        }

        //Threshold for button showing
        val jumpToTopButtonEnabled = feedListState.firstVisibleItemIndex > 2

        JumpToTopButton(
            // Only show if the scroller is not at the top
            enabled = jumpToTopButtonEnabled,
            onClicked = {
                //todo no way to scrollTo on the lazy lists currently. its internal.
            },
            modifier = Modifier.align(Alignment.BottomCenter).navigationBarsPadding()
        )
    }
}

@Composable
fun NewsFeedItem(index: Int, item: Article?, onItemSelected: (Article) -> Unit) {
    if (item != null) {
        val cardModifier = Modifier.run {
            if (index == 0) {
                padding(20.dp)
            } else {
                padding(
                    start = 20.dp,
                    top = 0.dp,
                    end = 20.dp,
                    bottom = 20.dp
                )
            }
        }

        ArticleItem(
            modifier = cardModifier,
            article = item,
            onClick = { article -> onItemSelected(article) })
    }
}

@Composable
fun NewsFeedLoading() {
    Box(
        modifier = Modifier.fillMaxWidth().height(100.dp),
        alignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun NewsFeedError(error: Throwable, onRetry: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxWidth().height(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Cant load the latest news. Check your internet.")
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = onRetry
        ) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun NewsFeedPageLoading() {
    Box(
        modifier = Modifier.fillMaxWidth().height(100.dp),
        alignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 4.dp),
            text = "Loading more..."
        )
    }
}

@Composable
fun NewsFeedPageError(error: Throwable, onRetry: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxWidth().height(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Cant load the more news. Check your internet.")
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = onRetry
        ) {
            Text(text = "Try again")
        }
    }
}

@Preview
@Composable
fun NewsFeedLoadingPreview() {
    EzNewsTheme() {
        Surface {
            NewsFeedLoading()
        }
    }
}

@Preview
@Composable
fun NewsFeedErrorPreview() {
    EzNewsTheme() {
        Surface {
            NewsFeedError(RuntimeException())
        }
    }
}

@Preview
@Composable
fun NewsFeedPageLoadingPreview() {
    EzNewsTheme() {
        Surface {
            NewsFeedPageLoading()
        }
    }
}

@Preview
@Composable
fun NewsFeedPageErrorPreview() {
    EzNewsTheme() {
        Surface {
            NewsFeedPageError(RuntimeException())
        }
    }
}