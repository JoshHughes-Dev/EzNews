package com.jhughes.eznews.articledetails.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.ui.tooling.preview.Preview
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.common.ui.WebComponent
import com.jhughes.eznews.common.utils.SysUiController
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.domain.model.Source
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import java.util.*

@Composable
fun ArticleDetails(article : Article, closeDetails : () -> Unit = {}) {

    with(SysUiController.current) {
        setStatusBarColor(MaterialTheme.colors.primarySurface)
        setNavigationBarColor(Color.Transparent)
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = article.author.toString()) },
            modifier = Modifier.statusBarsPadding(),
            navigationIcon = {
                IconButton(onClick = closeDetails) {
                    Icon(imageVector = Icons.Default.Close)
                }
            }
        )
    }) {
        WebComponent(urlToRender = article.url!!)
    }
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

@Preview
@Composable
fun ArticleDetailsPreview() {
    EzNewsTheme {
        ArticleDetails(article = dummyData)
    }
}