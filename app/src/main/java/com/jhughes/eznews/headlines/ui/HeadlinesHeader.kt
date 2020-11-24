package com.jhughes.eznews.headlines.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
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
            modifier = Modifier.fillMaxWidth().preferredHeight(56.dp)
                .padding(top = 8.dp),
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
                .padding(top = 16.dp, bottom = 8.dp)
        ) {
            FlowRow(
                mainAxisSize = SizeMode.Expand,
                mainAxisAlignment = FlowMainAxisAlignment.Center,
                crossAxisAlignment = FlowCrossAxisAlignment.Center,
                crossAxisSpacing = 8.dp
            ) {
                Text(text = "TOP")
                if (newsSelection.category != NewsCategory.ALL) {
                    Text(text = " ")
                    Button(onClick = onRequestSelectCategory) {
                        Text(text = newsSelection.category.name)
                    }
                }
                Text(text = " ")
                Text(text = "HEADLINES")
                Text(text = " ")
                Text(text = "ACROSS")
                Text(text = " ")
                Text(text = "THE")
                Text(text = " ")
                Button(onClick = onRequestSelectCountry) {
                    Text(text = newsSelection.country.name)
                }
            }
        }
        if (newsSelection.category == NewsCategory.ALL) {
            TextButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp),
                onClick = onRequestSelectCategory
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
    EzNewsTheme {
        Surface {
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
        Surface {
            HeadlinesHeader(
                newsSelection = HeadlinesPagingKey(
                    country = Country.UNITED_KINGDOM,
                    category = NewsCategory.TECHNOLOGY
                )
            )
        }
    }
}