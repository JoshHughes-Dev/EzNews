package com.jhughes.eznews.headlines.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.insets.statusBarsPadding
import com.jhughes.eznews.common.EzNewsNavigationActions
import com.jhughes.eznews.common.theme.SystemBarOpaque
import com.jhughes.eznews.common.utils.SysUiController
import com.jhughes.eznews.common.utils.backHandler
import com.jhughes.eznews.domain.model.HeadlinesPagingKey
import com.jhughes.eznews.headlines.HeadlinesViewModel
import com.jhughes.eznews.headlines.data.HeadlinesModalController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun TopHeadlines(viewModel: HeadlinesViewModel, navigationActions: EzNewsNavigationActions) {

    //we'll use a view to apply a scrim to status bar instead of coloring it directly.
    //This is so it still looks good when the bottom sheet modal scrim
    with(SysUiController.current) {
        setStatusBarColor(Color.Transparent, darkIcons = !isSystemInDarkTheme())
        setNavigationBarColor(Color.Transparent)
    }

    val newsSelection: State<HeadlinesPagingKey> = viewModel.newsSelection.collectAsState()

    HeadlinesModalBottomSheetLayout(viewModel = viewModel) { controller ->
        Box {
            Surface(color = MaterialTheme.colors.background) {
                NewsFeed(
                    headlinesViewModel = viewModel,
                    headerItem = {
                        HeadlinesHeader(
                            newsSelection = newsSelection.value,
                            onRequestSelectCategory = {
                                controller.showCategoryFilterModal()
                            },
                            onRequestSelectCountry = {
                                controller.showCountryFilterModal()
                            }
                        )
                    },
                    onSelectArticle = navigationActions.navigateToArticleDetails
                )
            }
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .statusBarsPadding()
                    .padding(top = 8.dp, end = 16.dp)
                    .size(48.dp),
                onClick = navigationActions.navigateToSettings
            ) {
                Icon(imageVector = Icons.Outlined.Settings, contentDescription = "")
            }
            //status bar scrim
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsHeight()
                    .background(MaterialTheme.colors.background.copy(alpha = SystemBarOpaque))
                    .align(Alignment.TopCenter)
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeadlinesModalBottomSheetLayout(
    viewModel: HeadlinesViewModel,
    mainContent: @Composable (controller: HeadlinesModalController) -> Unit
) {
    //nesting two bottom sheet modal because changing the sheet content dynamically causes issues
    val coroutineScope = rememberCoroutineScope()

    val categoryFilterSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val countryFilterSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    backHandler(
        enabled = categoryFilterSheetState.currentValue != ModalBottomSheetValue.Hidden,
        onBackPressed = { coroutineScope.launch { categoryFilterSheetState.hide() } }
    )
    backHandler(
        enabled = countryFilterSheetState.currentValue != ModalBottomSheetValue.Hidden,
        onBackPressed = { coroutineScope.launch { countryFilterSheetState.hide() } }
    )

    HeadlinesFilterModalBottomSheetLayout(
        sheetState = categoryFilterSheetState,
        sheetContent = {
            SelectCategory(modifier = Modifier.navigationBarsPadding()) { category ->
                viewModel.setNewsCategory(category)
                coroutineScope.launch { categoryFilterSheetState.hide() }
            }
        }
    ) {
        HeadlinesFilterModalBottomSheetLayout(
            sheetState = countryFilterSheetState,
            sheetContent = {
                SelectCountry(modifier = Modifier.navigationBarsPadding()) { country ->
                    viewModel.setCountry(country)
                    coroutineScope.launch { countryFilterSheetState.hide() }
                }
            },
            content = {
                mainContent(
                    HeadlinesModalController(
                        coroutineScope,
                        categoryFilterSheetState,
                        countryFilterSheetState
                    )
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeadlinesFilterModalBottomSheetLayout(
    sheetState: ModalBottomSheetState,
    sheetContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) = ModalBottomSheetLayout(
    sheetState = sheetState,
    sheetBackgroundColor = Color.Transparent,
    sheetContentColor = contentColorFor(backgroundColor = MaterialTheme.colors.surface),
    sheetElevation = 0.dp,
    sheetContent = {
        Box(modifier = Modifier.statusBarsPadding()) {
            Surface(
                elevation = ModalBottomSheetDefaults.Elevation,
            ) {
                sheetContent()
            }
        }
    },
    content = content
)

