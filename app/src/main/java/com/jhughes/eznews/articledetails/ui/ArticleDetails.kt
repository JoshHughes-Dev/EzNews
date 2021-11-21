package com.jhughes.eznews.articledetails.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jhughes.eznews.common.ui.WebComponent
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.domain.model.Source
import com.jhughes.eznews.headlines.HeadlinesViewModel
import java.util.*

@Composable
fun ArticleDetails(viewModel: HeadlinesViewModel, closeDetails : () -> Unit = {}) {

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()
    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = useDarkIcons)
    }

    val article = viewModel.selectedArticle

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = article.author.toString()) },
            modifier = Modifier.statusBarsPadding(),
            navigationIcon = {
                IconButton(onClick = closeDetails) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "")
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

//todo
//@Preview
//@Composable
//fun ArticleDetailsPreview() {
//    EzNewsTheme {
//        ArticleDetails(article = dummyData)
//    }
//}