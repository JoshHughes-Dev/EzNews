package com.jhughes.eznews.common

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.ui.tooling.preview.Preview
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.headlines.HeadlinesViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val headlinesViewModel: HeadlinesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EzNewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column() {
                        Row {
                            val newSelectionState = headlinesViewModel.newsSelection.collectAsState()
                            androidx.compose.material.Text(
                                text = newSelectionState.value.category.name
                            )
                            TextButton(onClick = { headlinesViewModel.randomCategory() }) {
                                androidx.compose.material.Text(text = "Randomise")
                            }
                        }
                        TestPagingThing(headlinesViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun TestPagingThing(headlinesViewModel: HeadlinesViewModel) {

    val lazyPagingItems: LazyPagingItems<Article> =
        headlinesViewModel.topHeadlines.collectAsLazyPagingItems()

    LazyColumn() {

        when (val refreshState = lazyPagingItems.loadState.refresh) {
            is LoadState.Loading -> {
                item {
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 4.dp),
                        text = "Full Loading"
                    )
                }
            }
            is LoadState.Error -> {
                item {
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 4.dp),
                        text = "Full Error: ${refreshState.error.toString()}"
                    )
                }
            }
        }

        if (lazyPagingItems.loadState.refresh is LoadState.NotLoading) {
            itemsIndexed(lazyPagingItems) { index, item ->
                if (item != null) {
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 4.dp),
                        text = item.title
                    )
                }
            }
        }

        when (val appendState = lazyPagingItems.loadState.append) {
            is LoadState.Loading -> {
                item {
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 4.dp),
                        text = "Loading next"
                    )
                }
            }
            is LoadState.Error -> {
                item {
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 4.dp),
                        text = "Error getting next: ${appendState.error.toString()}"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EzNewsTheme {
        Greeting("Android")
    }
}