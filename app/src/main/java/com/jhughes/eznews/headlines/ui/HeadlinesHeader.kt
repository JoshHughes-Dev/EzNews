package com.jhughes.eznews.headlines.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.domain.model.Country
import com.jhughes.eznews.domain.model.HeadlinesPagingKey
import com.jhughes.eznews.domain.model.NewsCategory
import com.jhughes.eznews.R
import com.jhughes.eznews.common.utils.toFlagEmoji

@OptIn(ExperimentalLayout::class)
@Composable
fun HeadlinesHeader(
    modifier: Modifier = Modifier,
    newsSelection: HeadlinesPagingKey,
    onRequestSelectCategory: () -> Unit = {},
    onRequestSelectCountry: () -> Unit = {}
) {
    Column(modifier) {
        Box(
            modifier = Modifier.fillMaxWidth().preferredHeight(56.dp)
                .padding(top = 8.dp),
            alignment = Alignment.Center
        ) {
            Image(
                asset = vectorResource(
                    if (!isSystemInDarkTheme()) {
                        R.drawable.ic_eznews_logo_light
                    } else {
                        R.drawable.ic_eznews_logo_dark
                    }
                )
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
                HeadlineTitleText(text = stringResource(id = R.string.top))
                if (newsSelection.category != NewsCategory.ALL) {
                    HeadlineTitleText(text = " ")
                    Button(
                        contentPadding = ButtonConstants.DefaultContentPadding.copy(
                            start = 8.dp,
                            end = 8.dp
                        ), onClick = onRequestSelectCategory
                    ) {
                        HeadlineTitleText(text = stringResource(id = newsSelection.category.res))
                    }
                }
                HeadlineTitleText(text = " ")
                HeadlineTitleText(text = stringResource(R.string.headlines))
                HeadlineTitleText(text = " ")
                HeadlineTitleText(text = stringResource(id = R.string.across))
                HeadlineTitleText(text = " ")
                HeadlineTitleText(text = stringResource(id = R.string.the))
                HeadlineTitleText(text = " ")
                Button(
                    contentPadding = ButtonConstants.DefaultContentPadding.copy(
                        start = 8.dp,
                        end = 8.dp
                    ),
                    onClick = onRequestSelectCountry
                ) {
                    newsSelection.country.let {
                        HeadlineTitleText(text = "${stringResource(id = it.res)} ${it.countryCode.toFlagEmoji()}")
                    }
                }
            }
        }
        if (newsSelection.category == NewsCategory.ALL) {
            TextButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp),
                onClick = onRequestSelectCategory
            ) {
                Text(text = stringResource(id = R.string.choose_category))
            }
        }
        Divider()
    }
}

@Composable
fun HeadlineTitleText(text : String) =
    Text(text = text, fontWeight = FontWeight.Bold)

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