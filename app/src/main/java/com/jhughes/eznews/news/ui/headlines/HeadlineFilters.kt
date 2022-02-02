package com.jhughes.eznews.news.ui.headlines

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.jhughes.eznews.common.ProvideAppTheme
import com.jhughes.eznews.common.data.AppTheme
import com.jhughes.eznews.common.theme.EzNewsBackdropContentTheme
import com.jhughes.eznews.common.ui.preview.LightDarkThemePreviewProvider
import com.jhughes.eznews.common.utils.toFlagEmoji
import com.jhughes.eznews.domain.model.Country
import com.jhughes.eznews.domain.model.NewsCategory
import com.jhughes.eznews.domain.model.NewsPagingKey
import com.jhughes.eznews.news.ui.ChipFilter
import com.jhughes.eznews.news.ui.common.KeywordFilter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeadlineFilters(
    modifier: Modifier = Modifier,
    categories: List<NewsCategory> = NewsCategory.values().toList(),
    countries: List<Country> = Country.usableSubSet().toList(),
    currentSelectionState: MutableState<NewsPagingKey.HeadlinesPagingKey> = mutableStateOf(NewsPagingKey.HeadlinesPagingKey())
) {
    Log.d("ComposeTest", "HeadlineFilters")

    Column(
        modifier = modifier
            .verticalScroll(ScrollState(0))
    ) {
        ChipFilter(
            title = "News Category",
            items = categories,
            selectedItem = currentSelectionState.value.category,
            onItemSelected = {
                currentSelectionState.value = currentSelectionState.value.copy(category = it)
            },
            onChipTitleFormat = { stringResource(id = it.res) }
        )
        ChipFilter(
            title = "Countries",
            items = countries,
            singleRow = true,
            selectedItem = currentSelectionState.value.country,
            onItemSelected = { currentSelectionState.value = currentSelectionState.value.copy(country = it)},
            onChipTitleFormat = { "${stringResource(id = it.res)} ${it.countryCode.toFlagEmoji()}" }
        )
        val focusManager = LocalFocusManager.current
        KeywordFilter(
            value = currentSelectionState.value.keyword ?: "",
            onValueChange = {
                currentSelectionState.value = currentSelectionState.value.copy(keyword = it)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
        )
    }
}

@Preview
@Composable
fun HeadlineFiltersPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme,
) {
    ProvideAppTheme(appTheme) {
        EzNewsBackdropContentTheme {
            HeadlineFilters(Modifier.background(MaterialTheme.colors.background))
        }
    }
}

