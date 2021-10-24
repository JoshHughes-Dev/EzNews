package com.jhughes.eznews.headlines.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.domain.model.NewsCategory
import com.jhughes.eznews.R
import com.jhughes.eznews.domain.model.emoji

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SelectCategory(
    modifier: Modifier = Modifier,
    onSelectCategory: (NewsCategory) -> Unit = {}
) {
    Column(
        modifier = modifier.padding(bottom = 8.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(12.dp),
            style = MaterialTheme.typography.h6,
            text = stringResource(id = R.string.select_category)
        )
        Divider()

        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
        ) {
            items(NewsCategory.values().toList()) { category ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(74.dp)
                        .clickable(onClick = { onSelectCategory(category) })
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = category.emoji())
                    Text(text = stringResource(id = category.res))
                }
            }
        }

    }
}

@Preview
@Composable
fun SelectCategoryPreview() {
    EzNewsTheme {
        Surface {
            SelectCategory()
        }
    }
}