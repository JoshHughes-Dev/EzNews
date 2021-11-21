package com.jhughes.eznews.headlines.ui

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropScaffoldDefaults
import androidx.compose.material.BackdropValue
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jhughes.eznews.common.theme.DarkColorPaletteAlt
import com.jhughes.eznews.common.theme.EzNewsThemeAlt
import com.jhughes.eznews.common.theme.LightColorPaletteAlt
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.domain.model.HeadlinesPagingKey
import com.jhughes.eznews.headlines.HeadlinesViewModel
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TopHeadlines(
    viewModel: HeadlinesViewModel,
    onArticleDetails: (Article) -> Unit = {},
    onSettings: () -> Unit
) {
    Log.d("ComposeTest", "TopHeadlines")

    val calendar = Calendar.getInstance()

    val lazyPagingItems: LazyPagingItems<Article> =
        viewModel.topHeadlines.collectAsLazyPagingItems()

    val isRefreshing by viewModel.isRefreshing.collectAsState()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
    val scope = rememberCoroutineScope()

    val newsSelection: State<HeadlinesPagingKey> = viewModel.newsSelection.collectAsState()

    BackHandler(enabled = scaffoldState.isRevealed) {
        scope.launch {
            scaffoldState.conceal()
        }
    }

    val systemUiController = rememberSystemUiController()
    val statusColor = MaterialTheme.colors.primary
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = statusColor.luminance() > 0.5f
        )
    }

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            viewModel.isRefreshing.value = true
            lazyPagingItems.refresh()
        }
    ) {
        val categoryState = remember { mutableStateOf(viewModel.newsSelection.value.category) }
        val countryState = remember { mutableStateOf(viewModel.newsSelection.value.country) }

        BackdropScaffold(
            modifier = Modifier.statusBarsPadding(),
            scaffoldState = scaffoldState,
            gesturesEnabled = false,
            persistentAppBar = true,
            peekHeight = BackdropScaffoldDefaults.PeekHeight + 8.dp,
            appBar = {
                EzNewsThemeAlt {
                    TopHeadlinesAppBar(
                        scaffoldState = scaffoldState,
                        onRequestFilters = {
                            if (scaffoldState.isConcealed) {
                                scope.launch { scaffoldState.reveal() }
                            } else {
                                scope.launch { scaffoldState.conceal() }
                            }
                        },
                        onRequestSettings = onSettings,
                        onConfirmFilters = {
                            viewModel.setFilters(categoryState.value, countryState.value)
                            scope.launch { scaffoldState.conceal() }
                        },
                        backgroundColor = if (!isSystemInDarkTheme()) LightColorPaletteAlt.primary else DarkColorPaletteAlt.background
                    )
                }
            },
            backLayerContent = {
                EzNewsThemeAlt {
                    HeadlineFilters(
                        modifier = Modifier.padding(bottom = 24.dp),
                        categoryState = categoryState,
                        countryState = countryState
                    )
                }
            },
            backLayerBackgroundColor = if(!isSystemInDarkTheme()) LightColorPaletteAlt.primary else DarkColorPaletteAlt.background,
            backLayerContentColor = if(!isSystemInDarkTheme()) LightColorPaletteAlt.onPrimary else DarkColorPaletteAlt.onBackground,
            frontLayerContent = {
                Column {
                    HeadlinesHeader(
                        modifier = Modifier.padding(16.dp, 8.dp),
                        newsSelection = newsSelection.value
                    )
                    Divider()
                    NewsFeed(
                        lazyPagingItems = lazyPagingItems,
                        calendar = calendar,
                        onArticleSelected = onArticleDetails,
                        onFinishedLoading = {
                            viewModel.isRefreshing.value = false
                        }
                    )
                }
            }
        )
    }
}

