package com.jhughes.eznews.common

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.core.view.WindowCompat
import com.jhughes.eznews.headlines.HeadlinesViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.jhughes.eznews.headlines.ui.TopHeadlines

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val headlinesViewModel: HeadlinesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            EzNewsApp(window = window) {
                TopHeadlines(headlinesViewModel)
            }
        }
    }
}