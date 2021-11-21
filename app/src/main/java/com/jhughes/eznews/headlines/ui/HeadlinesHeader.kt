package com.jhughes.eznews.headlines.ui

import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.jhughes.eznews.R
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.common.ui.preview.LightDarkThemePreviewProvider
import com.jhughes.eznews.common.utils.toFlagEmoji
import com.jhughes.eznews.domain.model.Country
import com.jhughes.eznews.domain.model.HeadlinesPagingKey
import com.jhughes.eznews.domain.model.NewsCategory
import com.jhughes.eznews.domain.model.emoji


@Composable
fun HeadlinesHeader(
    modifier: Modifier = Modifier,
    newsSelection: HeadlinesPagingKey = HeadlinesPagingKey()
) {
    FlowRow(
        modifier = modifier,
        mainAxisSize = SizeMode.Expand,
        mainAxisAlignment = FlowMainAxisAlignment.Center,
        crossAxisAlignment = FlowCrossAxisAlignment.Center,
        crossAxisSpacing = 8.dp
    ) {
        HeadlineTitleText(text = stringResource(id = R.string.top))
        if (newsSelection.category != NewsCategory.ALL) {
            HeadlineTitleText(text = " ")
            HeadlineTitleText(
                text = "${stringResource(id = newsSelection.category.res).uppercase()} ${newsSelection.category.emoji()}"
            )
        }
        HeadlineTitleText(text = " ")
        HeadlineTitleText(text = stringResource(R.string.headlines))
        HeadlineTitleText(text = " ")
        HeadlineTitleText(text = stringResource(id = R.string.across))
        HeadlineTitleText(text = " ")

        val countryWithThe = newsSelection.country == Country.UNITED_KINGDOM
                || newsSelection.country == Country.USA
        if (countryWithThe) {
            HeadlineTitleText(text = stringResource(id = R.string.the))
            HeadlineTitleText(text = " ")
        }
        newsSelection.country.let {
            HeadlineTitleText(text = "${stringResource(id = it.res).uppercase()} ${it.countryCode.toFlagEmoji()}")
        }
    }
}

@Composable
fun HeadlineTitleText(modifier: Modifier = Modifier, color: Color = Color.Unspecified, text: String) =
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )

@Preview
@Composable
fun HeadlinesHeaderPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) isDarkTheme : Boolean,
) {
    EzNewsTheme(isDarkTheme) {
        Surface {
            HeadlinesHeader()
        }
    }
}

@Preview
@Composable
fun HeadlinesHeaderPreview2(
    @PreviewParameter(HeadlinesPagingKeyPreviewProvider::class) pagingKey: HeadlinesPagingKey,
) {
    EzNewsTheme {
        Surface {
            HeadlinesHeader(newsSelection = pagingKey)
        }
    }
}

class HeadlinesPagingKeyPreviewProvider : PreviewParameterProvider<HeadlinesPagingKey> {
    override val values: Sequence<HeadlinesPagingKey> = sequenceOf(
        HeadlinesPagingKey(country = Country.BRAZIL, category = NewsCategory.BUSINESS),
    )
}