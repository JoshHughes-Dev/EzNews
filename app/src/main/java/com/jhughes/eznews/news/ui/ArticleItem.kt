package com.jhughes.eznews.news.ui

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.jhughes.eznews.common.ProvideAppTheme
import com.jhughes.eznews.common.data.AppTheme
import com.jhughes.eznews.common.isAppInDarkTheme
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.common.ui.preview.LightDarkThemePreviewProvider
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.domain.model.Source
import java.util.*
import kotlin.math.abs

@Composable
fun ArticleItem(
    modifier: Modifier = Modifier,
    article: Article?,
    calendar: Calendar,
    context: Context,
    onClick: (Article) -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = {
                article?.let { onClick(article) }
            })
            .padding(16.dp)
    ) {
        Column {
            article?.urlToImage?.let { ArticleImage(it) }
            Column(modifier = Modifier) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 12.dp),
                    text = formatArticleTitle(article?.title),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 6.dp),
                    text = formatArticleDescription(article?.description),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    style = MaterialTheme.typography.body1
                )
            }
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = formatSourceAndTimeSincePublished(article, calendar),
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.caption
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        article?.let { shareArticle(article, context) }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        modifier = Modifier.size(20.dp),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun ArticleImage(url: String) {

    val imageBackground = if (!isAppInDarkTheme()) {
        Color.LightGray
    } else {
        Color.DarkGray
    }
    Surface(
        modifier = Modifier.aspectRatio(1.80f).clip(RoundedCornerShape(16.dp)),
        color = imageBackground
    ) {

        val painter = rememberImagePainter(
            data = url,
            builder = {
                crossfade(true)
            }
        )
        Image(
            painter = painter,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )

        when (painter.state) {
            is ImagePainter.State.Loading -> {
                Box(Modifier.fillMaxSize()) {
                    Image(
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.Center),
                        imageVector = Icons.Outlined.Image,
                        colorFilter = ColorFilter.tint(contentColorFor(backgroundColor = Color.Gray)),
                        contentDescription = ""
                    )
                }
            }
            is ImagePainter.State.Error -> {
                Box(Modifier.fillMaxSize()) {
                    Image(
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.Center),
                        imageVector = Icons.Outlined.Error,
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.error),
                        contentDescription = ""
                    )
                }
            }
            else -> {
            }
        }
    }
}

private fun formatArticleTitle(title: String?): String {
    return title?.substringBeforeLast(delimiter = " - ") ?: ""
}

private fun formatArticleDescription(description: String?): String {
    return description ?: ""
}

private fun shareArticle(article: Article, context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TITLE, article.title)
        putExtra(Intent.EXTRA_TEXT, article.url)
    }
    context.startActivity(Intent.createChooser(intent, "Share Article"))
}

private fun formatSourceAndTimeSincePublished(article: Article?, calendar: Calendar): String {
    if (article == null) {
        return ""
    }
    val now = calendar.time
    val difference: Long = abs(article.publishedAt.time - now.time)
    val differenceInHours: Long = difference / (60 * 60 * 1000)

    return "${article.source.name} - $differenceInHours hours ago"
}

private val dummyData = Article(
    source = Source(null, "Metro.co.uk"),
    author = "GameCentral",
    title = "PS5 vs. Xbox Series X comparison – the console war where no one loses - Metro.co.uk",
    description = "Now that both next gen consoles are out GameCentral tries to compare the two and discovers it’s surprisingly hard to find common ground.",
    url = "https://metro.co.uk/2020/11/19/ps5-vs-xbox-series-x-comparison-the-console-war-where-no-one-loses-13618997/",
    urlToImage = "https://i1.wp.com/metro.co.uk/wp-content/uploads/2020/07/PS5_XBOX02-002-973b_1605775132.jpg?quality=90&strip=all&w=1200&h=630&crop=1&zoom=1&ssl=1",
    publishedAt = Date(),
)

@Preview(widthDp = 380)
@Composable
fun ArticleItemPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme
) {
    ProvideAppTheme(appTheme) {
        EzNewsTheme {
            Surface {
                NewsFeedItem(
                    item = dummyData,
                    calendar = Calendar.getInstance(),
                    context = LocalContext.current
                )
            }
        }
    }
}