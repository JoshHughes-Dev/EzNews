package com.jhughes.eznews.common

import android.view.Window
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.common.utils.AmbientBackDispatcher
import com.jhughes.eznews.common.utils.Navigator
import com.jhughes.eznews.common.utils.SysUiController
import com.jhughes.eznews.common.utils.SystemUiController
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@Composable
fun EzNewsApp(window : Window,
              backDispatcher: OnBackPressedDispatcher,
              content : @Composable (navigator : Navigator<Destination>, actions : Actions) -> Unit) {

    @Suppress("RemoveExplicitTypeArguments")
    val navigator: Navigator<Destination> = rememberSavedInstanceState(
        saver = Navigator.saver<Destination>(backDispatcher)
    ) {
        Navigator(Destination.TopHeadlines, backDispatcher)
    }
    val actions = remember(navigator) { Actions(navigator) }

    EzNewsTheme {
        val systemUiController = remember { SystemUiController(window) }
        Providers(SysUiController provides systemUiController) {
            Providers(AmbientBackDispatcher provides backDispatcher) {
                ProvideWindowInsets() {
                    content(navigator, actions)
                }
            }
        }
    }
}