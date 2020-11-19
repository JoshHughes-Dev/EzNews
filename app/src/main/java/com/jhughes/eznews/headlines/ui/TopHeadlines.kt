package com.jhughes.eznews.headlines.ui

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.unit.dp
import com.jhughes.eznews.headlines.HeadlinesViewModel
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import dev.chrisbanes.accompanist.insets.systemBarsPadding

@Composable
fun TopHeadlines(headlinesViewModel: HeadlinesViewModel) {
    // A surface container using the 'background' color from the theme
    WithConstraints {
        Box(modifier = Modifier.fillMaxSize()) {
            Surface(color = MaterialTheme.colors.background) {
                NewsFeed(
                    modifier = Modifier.systemBarsPadding(),
                    headlinesViewModel = headlinesViewModel
                ) {
                    Row(
                        modifier = Modifier.preferredHeightIn(48.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val newSelectionState = headlinesViewModel.newsSelection.collectAsState()
                        Text(text = newSelectionState.value.category.name)
                        TextButton(onClick = { headlinesViewModel.randomCategory() }) {
                            Text(text = "Randomise")
                        }
                    }
                }
            }
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .statusBarsPadding()
                    .padding(end = 16.dp)
                    .preferredSize(48.dp),
                onClick = { }
            ) {
                Icon(asset = Icons.Outlined.Settings)
            }
        }
    }
}