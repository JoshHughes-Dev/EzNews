package com.jhughes.eznews.headlines.ui

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.jhughes.eznews.common.theme.EzNewsThemeAlt
import com.jhughes.eznews.common.ui.preview.LightDarkThemePreviewProvider
import com.jhughes.eznews.common.utils.toFlagEmoji
import com.jhughes.eznews.domain.model.Country
import com.jhughes.eznews.domain.model.NewsCategory

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeadlineFilters(
    modifier: Modifier = Modifier,
    categories : List<NewsCategory> = NewsCategory.values().toList(),
    categoryState : MutableState<NewsCategory> = mutableStateOf(NewsCategory.ALL),
    countries : List<Country> = Country.usableSubSet().toList(),
    countryState: MutableState<Country> = mutableStateOf(Country.UNITED_KINGDOM),
) {
    Log.d("ComposeTest", "HeadlineFilters")

    Column(
        modifier = modifier
            .verticalScroll(ScrollState(0))
    ) {
        FilterGroup(
            title = "News Category",
            items = categories,
            selectedItem = categoryState.value,
            onItemSelected = { categoryState.value = it },
            onChipTitleFormat = { stringResource(id = it.res) }
        )
        Divider(modifier = Modifier.padding(start = 8.dp))
        FilterGroup(
            title = "Countries",
            items = countries,
            singleRow = true,
            selectedItem = countryState.value,
            onItemSelected = { countryState.value = it },
            onChipTitleFormat = { "${stringResource(id = it.res)} ${it.countryCode.toFlagEmoji()}" }
        )
    }
}

@Preview
@Composable
fun HeadlineFiltersPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) isDarkTheme: Boolean
) {
    EzNewsThemeAlt {
        HeadlineFilters()
    }
}

