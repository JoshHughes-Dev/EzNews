package com.jhughes.eznews.headlines.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jhughes.eznews.common.theme.SystemBarOpaque
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.domain.model.HeadlinesPagingKey
import com.jhughes.eznews.headlines.HeadlinesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun TopHeadlines(
    viewModel: HeadlinesViewModel,
    onSelectCategory: () -> Unit = {},
    onSelectCountry: () -> Unit = {},
    onArticleDetails: (Article) -> Unit = {},
    onSettings: () -> Unit
) {

    //we'll use a view to apply a scrim to status bar instead of coloring it directly.
    //This is so it still looks good when the bottom sheet modal scrim
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()
    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = useDarkIcons)
    }

    val newsSelection: State<HeadlinesPagingKey> = viewModel.newsSelection.collectAsState()

    Box {
        Surface(color = MaterialTheme.colors.background) {
            NewsFeed(
                headlinesViewModel = viewModel,
                headerItem = {
                    HeadlinesHeader(
                        newsSelection = newsSelection.value,
                        onRequestSelectCategory = onSelectCategory,
                        onRequestSelectCountry = onSelectCountry
                    )
                },
                onSelectArticle = onArticleDetails
            )
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .statusBarsPadding()
                .padding(top = 8.dp, end = 16.dp)
                .size(48.dp),
            onClick = onSettings
        ) {
            Icon(imageVector = Icons.Outlined.Settings, contentDescription = "")
        }
        //status bar scrim
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsHeight()
                .background(MaterialTheme.colors.background.copy(alpha = SystemBarOpaque))
                .align(Alignment.TopCenter)
        )
    }

}

