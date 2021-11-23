package com.jhughes.eznews.news.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp
import com.jhughes.eznews.R
import com.jhughes.eznews.common.ProvideAppTheme
import com.jhughes.eznews.common.data.AppTheme
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.common.ui.preview.LightDarkThemePreviewProvider
import com.jhughes.eznews.common.utils.toFlagEmoji
import com.jhughes.eznews.domain.model.Country
import com.jhughes.eznews.domain.model.HeadlinesPagingKey
import com.jhughes.eznews.domain.model.NewsCategory
import java.util.*


@Composable
fun HeadlinesHeader(
    modifier: Modifier = Modifier,
    newsSelection: HeadlinesPagingKey = HeadlinesPagingKey()
) {
    val text = StringJoiner(" ").apply {
        add(stringResource(id = R.string.top))
        if (newsSelection.category != NewsCategory.ALL) {
            add(stringResource(id = newsSelection.category.res).uppercase())
        }
        add(stringResource(R.string.headlines))
        add(stringResource(id = R.string.across))
        val countryWithThe = newsSelection.country == Country.UNITED_KINGDOM
                || newsSelection.country == Country.USA
        if (countryWithThe) {
            add(stringResource(id = R.string.the))
        }
        add(stringResource(id = newsSelection.country.res).uppercase())
        add(newsSelection.country.countryCode.toFlagEmoji())
    }.toString()

    HeadlineTitleText(
        modifier = modifier.fillMaxWidth(),
        text = text,
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadlineTitleText(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    text: String,
    textAlign: TextAlign? = null
) = Text(
    modifier = modifier,
    text = text,
    color = color,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    textAlign = textAlign
)

@Preview
@Composable
fun HeadlinesHeaderPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme
) {
    ProvideAppTheme(appTheme) {
        EzNewsTheme {
            Surface {
                HeadlinesHeader()
            }
        }
    }
}

@Preview
@Composable
fun HeadlinesHeaderPreview2(
) {
    ProvideAppTheme {
        EzNewsTheme {
            Surface {
                HeadlinesHeader(
                    newsSelection = HeadlinesPagingKey(
                        country = Country.BRAZIL,
                        category = NewsCategory.BUSINESS
                    )
                )
            }
        }
    }
}