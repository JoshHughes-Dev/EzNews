package com.jhughes.eznews.common.utils

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onCommit
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticAmbientOf

/**
 * An effect for handling presses of the device back button.
 */
@Composable
fun backHandler(
    enabled: Boolean = true,
    onBack: () -> Unit
) {
    val backCallback = remember(onBack) {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                onBack()
            }
        }
    }
    onCommit(enabled) {
        backCallback.isEnabled = enabled
    }

    val dispatcher = AmbientBackDispatcher.current
    onCommit(backCallback) {
        dispatcher.addCallback(backCallback)
        onDispose {
            backCallback.remove()
        }
    }
}

/**
 * An [androidx.compose.runtime.Ambient] providing the current [OnBackPressedDispatcher]. You must
 * [provide][androidx.compose.runtime.Providers] a value before use.
 */
internal val AmbientBackDispatcher = staticAmbientOf<OnBackPressedDispatcher> {
    error("No Back Dispatcher provided")
}