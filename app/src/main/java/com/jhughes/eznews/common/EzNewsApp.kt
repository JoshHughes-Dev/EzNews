package com.jhughes.eznews.common

import android.view.Window
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.remember
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.common.utils.AmbientBackDispatcher
import com.jhughes.eznews.common.utils.SysUiController
import com.jhughes.eznews.common.utils.SystemUiController
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@Composable
fun EzNewsApp(window : Window,
              backDispatcher: OnBackPressedDispatcher,
              content : @Composable () -> Unit) {
    EzNewsTheme {
        val systemUiController = remember { SystemUiController(window) }
        Providers(SysUiController provides systemUiController) {
            Providers(AmbientBackDispatcher provides backDispatcher) {
                ProvideWindowInsets() {
                    content()
                }
            }
        }
    }
}