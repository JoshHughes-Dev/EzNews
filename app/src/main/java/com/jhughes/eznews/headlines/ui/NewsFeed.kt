package com.jhughes.eznews.headlines.ui

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.jhughes.eznews.R
import com.jhughes.eznews.common.ProvideAppTheme
import com.jhughes.eznews.common.data.AppTheme
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.common.ui.preview.LightDarkThemePreviewProvider
import com.jhughes.eznews.domain.model.Article
import java.util.*

@Composable
fun NewsFeed(
    modifier: Modifier = Modifier,
    lazyPagingItems: LazyPagingItems<Article>,
    calendar: Calendar,
    onArticleSelected: (Article) -> Unit,
    onFinishedLoading: () -> Unit
) {
    Log.d("ComposeTest", "NewsFeed")
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier,
        contentPadding = rememberInsetsPaddingValues(
            insets = LocalWindowInsets.current.navigationBars
        )
    ) {
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
                onFinishedLoading()
            }
            is LoadState.NotLoading -> {
                items(lazyPagingItems) { item ->
                    NewsFeedItem(
                        item,
                        calendar = calendar,
                        context = context,
                        onItemSelected = onArticleSelected
                    )
                }
                onFinishedLoading()
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
}

@Composable
fun NewsFeedItem(
    item: Article?,
    calendar: Calendar,
    context: Context,
    onItemSelected: (Article) -> Unit = {}
) {
    Column {
        ArticleItem(
            article = item,
            calendar = calendar,
            context = context,
            onClick = onItemSelected
        )
        Divider()
    }
}

@Composable
fun NewsFeedLoading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun NewsFeedError(error: Throwable, onRetry: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.loading_error))
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = onRetry
        ) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Composable
fun NewsFeedPageLoading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 4.dp),
            text = stringResource(R.string.page_loading)
        )
    }
}

@Composable
fun NewsFeedPageError(error: Throwable, onRetry: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.loading_error))
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = onRetry
        ) {
            Text(text = stringResource(R.string.page_loading_error))
        }
    }
}

@Preview
@Composable
fun NewsFeedLoadingPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme
) {
    ProvideAppTheme(appTheme) {
        EzNewsTheme {
            Surface {
                NewsFeedLoading()
            }
        }
    }
}

@Preview
@Composable
fun NewsFeedErrorPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme
) {
    ProvideAppTheme(appTheme) {
        EzNewsTheme {
            Surface {
                NewsFeedError(RuntimeException())
            }
        }
    }
}

@Preview
@Composable
fun NewsFeedPageLoadingPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme
) {
    ProvideAppTheme(appTheme) {
        EzNewsTheme {
            Surface {
                NewsFeedPageLoading()
            }
        }
    }
}

@Preview
@Composable
fun NewsFeedPageErrorPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme
) {
    ProvideAppTheme(appTheme) {
        EzNewsTheme {
            Surface {
                NewsFeedPageError(RuntimeException())
            }
        }
    }
}
