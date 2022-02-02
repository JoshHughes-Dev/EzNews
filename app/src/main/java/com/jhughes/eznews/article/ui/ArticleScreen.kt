package com.jhughes.eznews.article.ui

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
import com.jhughes.eznews.article.ArticleViewModel
import com.jhughes.eznews.common.ui.TopScrimmedAppBar
import com.jhughes.eznews.common.ui.WebComponent

@Composable
fun ArticleScreen(viewModel: ArticleViewModel, closeDetails : () -> Unit = {}) {

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent)
    }

    val article = viewModel.article

    Scaffold(topBar = {
        TopScrimmedAppBar(
            title = { Text(text = article.source.name.orEmpty()) },
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