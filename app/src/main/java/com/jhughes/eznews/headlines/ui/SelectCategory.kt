package com.jhughes.eznews.headlines.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.domain.model.NewsCategory

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
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h6,
            text = "Select a news category"
        )
        Divider()
        LazyColumnFor(items = NewsCategory.values().toList()) { category ->
            Row(
                modifier = Modifier
                    .preferredHeight(48.dp)
                    .fillMaxWidth()
                    .clickable(onClick = { onSelectCategory(category) })
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = category.name.toString()
                )
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