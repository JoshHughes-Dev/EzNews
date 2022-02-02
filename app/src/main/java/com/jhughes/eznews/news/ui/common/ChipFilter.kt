package com.jhughes.eznews.news.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.jhughes.eznews.common.ProvideAppTheme
import com.jhughes.eznews.common.data.AppTheme
import com.jhughes.eznews.common.theme.EzNewsBackdropContentTheme
import com.jhughes.eznews.common.ui.SelectableChip
import com.jhughes.eznews.common.ui.preview.LightDarkThemePreviewProvider
import com.jhughes.eznews.common.utils.toFlagEmoji
import com.jhughes.eznews.domain.model.Country
import com.jhughes.eznews.domain.model.NewsCategory


@Composable
fun <T> ChipFilter(
    modifier: Modifier = Modifier,
    title: String = "Group",
    items: List<T> = emptyList(),
    singleRow: Boolean = false,
    selectedItem: T,
    onItemSelected: (T) -> Unit = {},
    onChipTitleFormat: @Composable (T) -> String,
) {
    FilterSection(
        modifier = modifier,
        title = title,
    ) {
        if (singleRow) {
            LazyRow(
                contentPadding = PaddingValues(8.dp)
            ) {
                items(items) { item ->
                    SelectableChip(
                        modifier = Modifier.padding(end = 8.dp),
                        label = onChipTitleFormat(item),
                        contentDescription = "",
                        selected = item == selectedItem,
                        onClick = { onItemSelected(item) }
                    )
                }
            }
        } else {
            FlowRow(
                modifier = Modifier.padding(8.dp),
                crossAxisSpacing = 8.dp
            ) {
                items.forEach { item ->
                    SelectableChip(
                        modifier = Modifier.padding(end = 8.dp),
                        label = onChipTitleFormat(item),
                        contentDescription = "",
                        selected = item == selectedItem,
                        onClick = { onItemSelected(item) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun FilterGroupPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme
) {
    ProvideAppTheme(appTheme) {
        EzNewsBackdropContentTheme {
            ChipFilter(
                modifier = Modifier.background(MaterialTheme.colors.background),
                title = "News Category",
                items = NewsCategory.values().toList(),
                singleRow = false,
                selectedItem = NewsCategory.ALL,
                onChipTitleFormat = { stringResource(id = it.res) }
            )
        }
    }
}

@Preview
@Composable
fun FilterGroupPreview2(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme
) {
    ProvideAppTheme(appTheme) {
        EzNewsBackdropContentTheme {
            ChipFilter(
                modifier = Modifier.background(MaterialTheme.colors.background),
                title = "Countries",
                singleRow = true,
                items = Country.usableSubSet().toList(),
                selectedItem = Country.UNITED_KINGDOM,
                onChipTitleFormat = { "${stringResource(id = it.res)} ${it.countryCode.toFlagEmoji()}" }
            )
        }
    }
}
