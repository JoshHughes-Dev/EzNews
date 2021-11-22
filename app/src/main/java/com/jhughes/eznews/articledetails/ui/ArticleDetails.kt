package com.jhughes.eznews.articledetails.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jhughes.eznews.common.ui.TopScrimmedAppBar
import com.jhughes.eznews.common.ui.WebComponent
import com.jhughes.eznews.headlines.HeadlinesViewModel

@Composable
fun ArticleDetails(viewModel: HeadlinesViewModel, closeDetails : () -> Unit = {}) {

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent)
    }

    val article = viewModel.selectedArticle

    Scaffold(topBar = {
        TopScrimmedAppBar(
            title = { Text(text = article.author.toString()) },
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