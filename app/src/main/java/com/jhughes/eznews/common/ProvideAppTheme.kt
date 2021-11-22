package com.jhughes.eznews.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.jhughes.eznews.common.data.AppTheme

val LocalAppTheme = staticCompositionLocalOf<AppTheme> { error("No Theme") }

@Composable
fun ProvideAppTheme(
    currentAppTheme: AppTheme = AppTheme.SYSTEM,
    content: @Composable () -> Unit
) = CompositionLocalProvider(LocalAppTheme provides currentAppTheme) { content() }

@Composable
fun isAppInDarkTheme(): Boolean {
    return when (LocalAppTheme.current) {
        AppTheme.LIGHT -> false
        AppTheme.DARK -> true
        AppTheme.SYSTEM -> isSystemInDarkTheme()
    }
}