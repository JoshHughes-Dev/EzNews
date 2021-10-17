package com.jhughes.eznews.common

import android.view.Window
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.google.accompanist.insets.ProvideWindowInsets
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.common.utils.LocalBackPressedDispatcher
import com.jhughes.eznews.common.utils.SysUiController
import com.jhughes.eznews.common.utils.SystemUiController
import com.squareup.moshi.Moshi

@Composable
fun EzNewsApp(
    window: Window,
    backDispatcher: OnBackPressedDispatcher,
    moshi : Moshi
) {

    EzNewsTheme {
        val systemUiController = remember { SystemUiController(window) }
        CompositionLocalProvider(
            SysUiController provides systemUiController,
            LocalBackPressedDispatcher provides backDispatcher
        ) {
            ProvideWindowInsets {
                EzNewsCoordinator(moshi = moshi)
            }
        }
    }
}