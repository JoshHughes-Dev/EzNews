package com.jhughes.eznews.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Blue200,
    primaryVariant = Blue400,
    onPrimary = Color.Black,
    onSurface = Color.White,
    onBackground = Color.White,
)

val DarkColorPaletteAlt = darkColors(
    primary = Color.Black,
    onPrimary = Blue200,
    background = Color.Black,
    onBackground = Blue200,
    onSurface = Blue200,
)

private val LightColorPalette = lightColors(
    primary = BlueM800,
    primaryVariant = BlueM900,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
)

val LightColorPaletteAlt = lightColors(
    primary = BlueM800,
    primaryVariant = BlueM300,
    onPrimary = Color.White,
    surface = BlueM500,
    onSurface = Color.White,
    background = BlueM700,
    onBackground = Color.White,
)

@Composable
fun EzNewsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

@Composable
fun EzNewsThemeAlt(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) {
        DarkColorPaletteAlt
    } else {
        LightColorPaletteAlt
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapesAlt,
        content = content
    )
}