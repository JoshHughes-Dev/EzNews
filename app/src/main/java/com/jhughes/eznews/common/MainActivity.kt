package com.jhughes.eznews.common

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.jhughes.eznews.common.theme.EzNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            Log.d("ComposeTest", "setContent")

            EzNewsTheme {
                ProvideWindowInsets {

                    EzNewsCoordinator()
                }
            }
        }
    }
}