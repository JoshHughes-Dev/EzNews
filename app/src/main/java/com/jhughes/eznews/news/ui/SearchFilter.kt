package com.jhughes.eznews.news.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.jhughes.eznews.common.ProvideAppTheme
import com.jhughes.eznews.common.data.AppTheme
import com.jhughes.eznews.common.theme.EzNewsBackdropContentTheme
import com.jhughes.eznews.common.ui.preview.LightDarkThemePreviewProvider
import com.jhughes.eznews.domain.model.NewsPagingKey
import com.jhughes.eznews.news.ui.everything.EverythingFilters
import com.jhughes.eznews.news.ui.headlines.HeadlineFilters

@Composable
fun SearchFilter(
    selectedPagingKeyState: MutableState<NewsPagingKey>,
    onTabSelected : (Int) -> Unit
) {
    Column {
        NewsFilterTabRow(tabs = tabData) { tab ->
            onTabSelected(tabData.indexOf(tab))
        }
        when (selectedPagingKeyState.value) {
            is NewsPagingKey.HeadlinesPagingKey -> {
                HeadlineFilters(
                    modifier = Modifier.padding(bottom = 24.dp),
                    currentSelectionState = selectedPagingKeyState as MutableState<NewsPagingKey.HeadlinesPagingKey>
                )
            }
            is NewsPagingKey.EverythingPagingKey -> {
                EverythingFilters()
            }
        }
    }
}

@Preview
@Composable
fun SearchFilterPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme
) {
    ProvideAppTheme(appTheme) {
        EzNewsBackdropContentTheme {
            Surface(color = MaterialTheme.colors.background) {
                SearchFilter(
                    selectedPagingKeyState = remember { mutableStateOf(NewsPagingKey.HeadlinesPagingKey()) },
                    onTabSelected = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchFilterPreview2(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme
) {
    ProvideAppTheme(appTheme) {
        EzNewsBackdropContentTheme {
            Surface(color = MaterialTheme.colors.background) {
                SearchFilter(
                    selectedPagingKeyState = remember { mutableStateOf(NewsPagingKey.EverythingPagingKey()) },
                    onTabSelected = {}
                )
            }
        }
    }
}
