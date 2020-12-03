package com.jhughes.eznews.headlines.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.domain.model.Source
import dev.chrisbanes.accompanist.coil.CoilImage
import java.util.*
import kotlin.math.abs

@Composable
fun ArticleItem(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (Article) -> Unit = {},
) {
    val context = AmbientContext.current
    Card(
        elevation = 6.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.fillMaxWidth()
            .clickable(onClick = {
                openArticle(article, context)
                //onClick(article)
            })
    ) {
        Column() {
            val imageBackground = if (!isSystemInDarkTheme()) {
                Color.LightGray
            } else {
                Color.DarkGray
            }
            Surface(color = imageBackground) {
                CoilImage(
                    modifier = Modifier.aspectRatio(1.80f),
                    data = article.urlToImage ?: "",
                    contentScale = ContentScale.Crop,
                    fadeIn = true,
                    loading = {
                        Box(Modifier.fillMaxWidth()) {
                            Image(
                                modifier = Modifier.preferredSize(24.dp).align(Alignment.Center),
                                imageVector = Icons.Outlined.Image,
                                colorFilter = ColorFilter.tint(contentColorFor(color = Color.Gray))
                            )
                        }
                    },
                    error = {
                        Box(Modifier.fillMaxWidth()) {
                            Image(
                                modifier = Modifier.preferredSize(24.dp).align(Alignment.Center),
                                imageVector = Icons.Outlined.Error,
                                colorFilter = ColorFilter.tint(MaterialTheme.colors.error)
                            )
                        }
                    }
                )
            }
            Column(modifier = Modifier) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 12.dp),
                    text = formatArticleTitle(article.title),
                    style = MaterialTheme.typography.h6
                )
                if (article.description?.isNotEmpty() == true) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 6.dp),
                        text = article.description,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        style = MaterialTheme.typography.body1
                    )
                }
                Row(
                    modifier = Modifier.wrapContentHeight()
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = article.source.name.toString(),
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.caption
                    )
                    Text(
                        text = " - ${formatTimeSincePublished(article.publishedAt)}",
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.caption
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = { shareArticle(article, context) }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Share,
                            modifier = Modifier.preferredSize(20.dp)
                        )
                    }
                }
            }
        }
    }
}

private fun formatArticleTitle(title: String): String {
    return title.substringBeforeLast(delimiter = " - ")
}

private fun shareArticle(article: Article, context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TITLE, article.title)
        putExtra(Intent.EXTRA_TEXT, article.url)
    }
    context.startActivity(Intent.createChooser(intent, "Share Article"))
}

private fun openArticle(article: Article, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
    context.startActivity(intent)
}

private fun formatTimeSincePublished(date: Date): String {
    val now = Calendar.getInstance().time
    val difference: Long = abs(date.time - now.time)
    val differenceInHours: Long = difference / (60 * 60 * 1000)
    return "$differenceInHours hours ago"
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
fun ArticleItemPreview() {
    EzNewsTheme(darkTheme = false) {
        ArticleItem(article = dummyData)
    }
}

@Preview(widthDp = 380)
@Composable
fun ArticleItemPreviewDark() {
    EzNewsTheme(darkTheme = true) {
        ArticleItem(article = dummyData)
    }
}