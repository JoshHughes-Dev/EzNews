package com.jhughes.eznews.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onCommit
import androidx.compose.ui.graphics.Color
import com.jhughes.eznews.common.utils.SysUiController

private val DarkColorPalette = darkColors(
    primary = Blue200,
    primaryVariant = Blue400,
    onPrimary = Color.Black,
    secondary = Yellow400,
    onSecondary = Color.Black,
    onSurface = Color.White,
    onBackground = Color.White,
    error = Red300,
    onError = Color.Black
)

private val LightColorPalette = lightColors(
    primary = Blue500,
    primaryVariant = Blue800,
    onPrimary = Color.White,
    secondary = Yellow700,
    secondaryVariant = Yellow800,
    onSecondary = Color.Black,
    onSurface = Color.Black,
    onBackground = Color.Black,
    error = Red800,
    onError = Color.White
)

@Composable
fun EzNewsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit) {

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val sysUiController = SysUiController.current
    onCommit(sysUiController, colors.surface) {
        sysUiController.setSystemBarsColor(
            color = colors.surface.copy(alpha = AlphaNearOpaque)
        )
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}