package com.jhughes.eznews.common

import android.view.Window
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.google.accompanist.insets.ProvideWindowInsets
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.common.utils.LocalBackPressedDispatcher
import com.jhughes.eznews.common.utils.Navigator
import com.jhughes.eznews.common.utils.SysUiController
import com.jhughes.eznews.common.utils.SystemUiController

@Composable
fun EzNewsApp(
    window: Window,
    backDispatcher: OnBackPressedDispatcher,
    content: @Composable (navigator: Navigator<Destination>, actions: Actions) -> Unit
) {

    @Suppress("RemoveExplicitTypeArguments")
    val navigator: Navigator<Destination> = rememberSaveable(
        saver = Navigator.saver<Destination>(backDispatcher)
    ) {
        Navigator(Destination.TopHeadlines, backDispatcher)
    }
    val actions = remember(navigator) { Actions(navigator) }

    EzNewsTheme {
        val systemUiController = remember { SystemUiController(window) }
        CompositionLocalProvider(
            SysUiController provides systemUiController,
            LocalBackPressedDispatcher provides backDispatcher
        ) {
            ProvideWindowInsets {
                content(navigator, actions)
            }
        }
    }
}