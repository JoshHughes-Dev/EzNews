package com.jhughes.eznews.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ElevationOverlay
import androidx.compose.material.LocalElevationOverlay
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jhughes.eznews.common.LocalAppTheme
import com.jhughes.eznews.common.data.AppTheme
import kotlin.math.ln

private val DarkColorPalette = darkColors(
    primary = Blue200,
    onPrimary = Color.Black
)

private val BackDropDarkColorPalette = DarkColorPalette.copy(
    background = Color.Black
)

private val BackDropContentDarkColorPalette = DarkColorPalette.copy(
    primary = Color.Black,
    onPrimary = Blue200,
    background = Color.Black,
    onBackground = Blue200,
    onSurface = Blue200,
)

private val LightColorPalette = lightColors(
    primary = BlueM800,
    primaryVariant = BlueM900,
)

private val BackDropContentLightColorPalette = LightColorPalette.copy(
    primary = BlueM700,
    primaryVariant = BlueM300,
    background = BlueM700,
    onBackground = Color.White,
    surface = BlueM500,
    onSurface = Color.White,
)

@Composable
fun EzNewsTheme(
    appTheme: AppTheme = LocalAppTheme.current,
    content: @Composable () -> Unit
) {
    val colors = when (appTheme) {
        AppTheme.LIGHT -> LightColorPalette
        AppTheme.DARK -> DarkColorPalette
        AppTheme.SYSTEM -> if (isSystemInDarkTheme()) {
            DarkColorPalette
        } else {
            LightColorPalette
        }
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        content = content
    )
}

@Composable
fun EzNewsBackdropTheme(
    appTheme: AppTheme = LocalAppTheme.current,
    content: @Composable () -> Unit
) {
    val colors = when (appTheme) {
        AppTheme.LIGHT -> LightColorPalette
        AppTheme.DARK -> BackDropDarkColorPalette
        AppTheme.SYSTEM -> if (isSystemInDarkTheme()) {
            BackDropDarkColorPalette
        } else {
            LightColorPalette
        }
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
    ) {
        CompositionLocalProvider(LocalElevationOverlay provides CustomElevationOverlay) {
            content()
        }
    }
}

@Composable
fun EzNewsBackdropContentTheme(
    appTheme: AppTheme = LocalAppTheme.current,
    content: @Composable () -> Unit
) {
    val colors = when (appTheme) {
        AppTheme.LIGHT -> BackDropContentLightColorPalette
        AppTheme.DARK -> BackDropContentDarkColorPalette
        AppTheme.SYSTEM -> if (isSystemInDarkTheme()) {
            BackDropContentDarkColorPalette
        } else {
            BackDropContentLightColorPalette
        }
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        content = content
    )
}

object CustomElevationOverlay : ElevationOverlay {
    @ReadOnlyComposable
    @Composable
    override fun apply(color: Color, elevation: Dp): Color {
        val colors = MaterialTheme.colors
        return if (elevation > 0.dp && !colors.isLight) {
            val foregroundColor = calculateForegroundColor(color, elevation)
            foregroundColor.compositeOver(color)
        } else if (elevation == 0.dp && !colors.isLight) {
            MaterialTheme.colors.background
        } else {
            color
        }
    }
}

@ReadOnlyComposable
@Composable
private fun calculateForegroundColor(backgroundColor: Color, elevation: Dp): Color {
    val alpha = ((4.5f * ln(elevation.value + 1)) + 2f) / 100f
    val baseForegroundColor = contentColorFor(backgroundColor)
    return baseForegroundColor.copy(alpha = alpha)
}