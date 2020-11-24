package com.jhughes.eznews.headlines.ui

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
import com.jhughes.eznews.common.theme.SystemBarOpaque
import com.jhughes.eznews.common.utils.SysUiController
import com.jhughes.eznews.common.utils.backHandler
import com.jhughes.eznews.domain.model.HeadlinesPagingKey
import com.jhughes.eznews.headlines.HeadlinesViewModel
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TopHeadlines(viewModel: HeadlinesViewModel) {

    with(SysUiController.current) {
        setStatusBarColor(MaterialTheme.colors.background.copy(alpha = SystemBarOpaque))
        setNavigationBarColor(Color.Transparent)
    }

    val categoryFilterSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val countryFilterSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val newsSelection: State<HeadlinesPagingKey> = viewModel.newsSelection.collectAsState()

    HeadlinesModalBottomSheetLayout(
        viewModel = viewModel,
        categoryFilterSheetState = categoryFilterSheetState,
        countryFilterSheetState = countryFilterSheetState
    ) {
        Box {
            Surface(color = MaterialTheme.colors.background) {
                NewsFeed(
                    headlinesViewModel = viewModel,
                    headerItem = {
                        HeadlinesHeader(
                            modifier = Modifier.statusBarsPadding(),
                            newsSelection = newsSelection.value,
                            onRequestSelectCategory = {
                                categoryFilterSheetState.show()
                            },
                            onRequestSelectCountry = {
                                countryFilterSheetState.show()
                            }
                        )
                    }
                )
            }
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .statusBarsPadding()
                    .padding(end = 16.dp)
                    .preferredSize(48.dp),
                onClick = { }
            ) {
                Icon(asset = Icons.Outlined.Settings)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun HeadlinesModalBottomSheetLayout(
    viewModel: HeadlinesViewModel,
    categoryFilterSheetState : ModalBottomSheetState,
    countryFilterSheetState : ModalBottomSheetState,
    mainContent: @Composable () -> Unit
) {
    //nesting two bottom sheet modal because changing the sheet content dynamically causes issues

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
        sheetContent = { modifier ->
            SelectCategory(modifier = modifier) { category ->
                viewModel.setNewsCategory(category)
                categoryFilterSheetState.hide()
            }
        }
    ) {
        HeadlinesFilterModalBottomSheetLayout(
            sheetState = countryFilterSheetState,
            sheetContent = { modifier ->
                SelectCountry(modifier = modifier) { country ->
                    viewModel.setCountry(country)
                    countryFilterSheetState.hide()
                }
            },
            content = mainContent
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeadlinesFilterModalBottomSheetLayout(
    sheetState: ModalBottomSheetState,
    sheetContent: @Composable (Modifier) -> Unit,
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
                sheetContent(Modifier)
            }
        }
    },
    content = content
)

