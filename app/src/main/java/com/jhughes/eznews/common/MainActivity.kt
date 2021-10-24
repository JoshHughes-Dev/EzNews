package com.jhughes.eznews.common

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.squareup.moshi.Moshi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            EzNewsTheme {
                ProvideWindowInsets {
                    EzNewsCoordinator()
                }
            }
        }
    }
}