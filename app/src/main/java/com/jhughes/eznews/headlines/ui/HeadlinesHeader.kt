package com.jhughes.eznews.headlines.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.domain.model.Country
import com.jhughes.eznews.domain.model.HeadlinesPagingKey
import com.jhughes.eznews.domain.model.NewsCategory

@OptIn(ExperimentalLayout::class)
@Composable
fun HeadlinesHeader(
    modifier: Modifier = Modifier,
    newsSelection: HeadlinesPagingKey,
    onRequestSelectCategory : () -> Unit = {},
    onRequestSelectCountry : () -> Unit = {}
) {
    Column(modifier) {
        Box(
            modifier = Modifier.fillMaxWidth().preferredHeight(54.dp).padding(8.dp),
            alignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 54.dp),
                text = "EzNews",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5
            )
        }

        Box(
            Modifier.fillMaxWidth().padding(horizontal = 12.dp)
                .padding(bottom = 8.dp)
        ) {
            FlowRow(
                mainAxisSize = SizeMode.Expand,
                mainAxisAlignment = FlowMainAxisAlignment.Center,
                crossAxisAlignment = FlowCrossAxisAlignment.Center,
                crossAxisSpacing = 8.dp
            ) {
                Text(text = "Top ")
                if (newsSelection.category != NewsCategory.ALL) {
                    Button(onClick = {
                        onRequestSelectCategory()
                    }) {
                        Text(text = newsSelection.category.name)
                    }
                }
                Text(text = " headlines")
                Text(text = " across")
                Text(text = " the ")
                Button(onClick = {
                    onRequestSelectCountry()
                }) {
                    Text(text = newsSelection.country.name)
                }
            }
        }
        if (newsSelection.category == NewsCategory.ALL) {
            TextButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp),
                onClick = {
                    onRequestSelectCategory()
                }
            ) {
                Text(text = "Choose a category")
            }
        }
        Divider()
    }
}

@Preview
@Composable
fun HeaderPreview() {
    EzNewsTheme() {
        Surface() {
            HeadlinesHeader(
                newsSelection = HeadlinesPagingKey(
                    country = Country.USA,
                    category = NewsCategory.HEALTH
                )
            )
        }
    }
}

@Preview
@Composable
fun HeaderPreviewAll() {
    EzNewsTheme() {
        Surface() {
            HeadlinesHeader(
                newsSelection = HeadlinesPagingKey(
                    country = Country.UNITED_KINGDOM,
                    category = NewsCategory.ALL
                )
            )
        }
    }
}

@Preview
@Composable
fun HeaderPreviewDark() {
    EzNewsTheme(darkTheme = true) {
        Surface() {
            HeadlinesHeader(
                newsSelection = HeadlinesPagingKey(
                    country = Country.UNITED_KINGDOM,
                    category = NewsCategory.TECHNOLOGY
                )
            )
        }
    }
}