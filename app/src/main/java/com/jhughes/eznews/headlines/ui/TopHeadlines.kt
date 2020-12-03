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
import com.jhughes.eznews.common.Actions
import com.jhughes.eznews.common.theme.SystemBarOpaque
import com.jhughes.eznews.common.utils.SysUiController
import com.jhughes.eznews.common.utils.backHandler
import com.jhughes.eznews.domain.model.HeadlinesPagingKey
import com.jhughes.eznews.headlines.HeadlinesViewModel
import com.jhughes.eznews.headlines.data.HeadlinesModalController
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import dev.chrisbanes.accompanist.insets.statusBarsHeight
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun TopHeadlines(viewModel: HeadlinesViewModel, actions: Actions) {

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
                    onSelectArticle = actions.selectArticle
                )
            }
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .statusBarsPadding()
                    .padding(top = 8.dp, end = 16.dp)
                    .preferredSize(48.dp),
                onClick = actions.showSettings
            ) {
                Icon(imageVector = Icons.Outlined.Settings)
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

    val categoryFilterSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val countryFilterSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    backHandler(
        enabled = categoryFilterSheetState.value != ModalBottomSheetValue.Hidden,
        onBack = { categoryFilterSheetState.hide() }
    )
    backHandler(
        enabled = countryFilterSheetState.value != ModalBottomSheetValue.Hidden,
        onBack = { countryFilterSheetState.hide() }
    )

    HeadlinesFilterModalBottomSheetLayout(
        sheetState = categoryFilterSheetState,
        sheetContent = {
            SelectCategory(modifier = Modifier.navigationBarsPadding()) { category ->
                viewModel.setNewsCategory(category)
                categoryFilterSheetState.hide()
            }
        }
    ) {
        HeadlinesFilterModalBottomSheetLayout(
            sheetState = countryFilterSheetState,
            sheetContent = {
                SelectCountry(modifier = Modifier.navigationBarsPadding()) { country ->
                    viewModel.setCountry(country)
                    countryFilterSheetState.hide()
                }
            },
            content = {
                mainContent(
                    HeadlinesModalController(
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
    sheetContentColor = contentColorFor(color = MaterialTheme.colors.surface),
    sheetElevation = 0.dp,
    sheetContent = {
        Box(modifier = Modifier.statusBarsPadding()) {
            Surface(
                elevation = ModalBottomSheetConstants.DefaultElevation,
            ) {
                sheetContent()
            }
        }
    },
    content = content
)

