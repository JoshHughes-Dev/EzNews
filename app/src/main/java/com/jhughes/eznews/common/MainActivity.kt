package com.jhughes.eznews.common

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.core.view.WindowCompat
import com.jhughes.eznews.articledetails.ui.ArticleDetails
import com.jhughes.eznews.common.data.toDomain
import com.jhughes.eznews.headlines.HeadlinesViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.jhughes.eznews.headlines.ui.TopHeadlines
import com.jhughes.eznews.settings.ui.SettingsLayout

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val headlinesViewModel: HeadlinesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            EzNewsApp(
                window = window,
                backDispatcher = onBackPressedDispatcher
            ) { navigator, actions ->

                Crossfade(navigator.current) { destination ->
                    when (destination) {
                        is Destination.TopHeadlines -> TopHeadlines(
                            viewModel = headlinesViewModel,
                            actions = actions
                        )
                        is Destination.Settings -> SettingsLayout(closeSettings = actions.upPress)
                        is Destination.ArticleDetails -> {
                            ArticleDetails(
                                article = destination.article.toDomain(),
                                closeDetails = actions.upPress
                            )
                        }
                    }
                }
            }
        }
    }
}