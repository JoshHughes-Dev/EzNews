package com.jhughes.eznews.news.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jhughes.eznews.common.ProvideAppTheme
import com.jhughes.eznews.common.data.AppTheme
import com.jhughes.eznews.common.theme.EzNewsBackdropContentTheme
import com.jhughes.eznews.common.ui.preview.LightDarkThemePreviewProvider

data class Tab(val label: String)

val tabData = listOf(
    Tab("Headlines"),
    Tab("Everything"),
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NewsFilterTabRow(
    modifier: Modifier = Modifier,
    tabs: List<Tab>,
    onTabSelected: (Tab) -> Unit
) {

    val selectedTab by remember { mutableStateOf(0) }

    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTab,
        backgroundColor = MaterialTheme.colors.background
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = { Text(text = tab.label) },
                selected = selectedTab == index,
                onClick = {
                    onTabSelected(tabs[index])
                }
            )
        }
    }
}

@Preview
@Composable
fun NewsFilterPagerPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme
) {
    ProvideAppTheme(appTheme) {
        EzNewsBackdropContentTheme {
            NewsFilterTabRow(tabs = tabData) {}
        }
    }
}