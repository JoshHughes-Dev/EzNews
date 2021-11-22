package com.jhughes.eznews.common

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.jhughes.eznews.common.data.AppTheme
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.data.prefs.AppPrefsStore
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var appPrefsStore: AppPrefsStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            Log.d("ComposeTest", "setContent")

            val theme = appPrefsStore.getAppTheme.collectAsState(initial = AppTheme.SYSTEM)

            ProvideAppTheme(currentAppTheme = theme.value) {
                EzNewsTheme {
                    ProvideWindowInsets {
                        EzNewsCoordinator()
                    }
                }
            }
        }
    }
}