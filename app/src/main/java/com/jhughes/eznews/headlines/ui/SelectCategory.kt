package com.jhughes.eznews.headlines.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.common.ui.LazyGridFor
import com.jhughes.eznews.domain.model.NewsCategory
import com.jhughes.eznews.R
import com.jhughes.eznews.common.utils.toFlagEmoji
import com.jhughes.eznews.domain.model.emoji

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
        LazyGridFor(
            items = NewsCategory.values().toList(),
            rowSize = 2
        ) { category ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .preferredHeight(74.dp)
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

@Preview
@Composable
fun SelectCategoryPreview() {
    EzNewsTheme {
        Surface {
            SelectCategory()
        }
    }
}