package com.jhughes.eznews.common.ui

import android.annotation.SuppressLint
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebComponent(urlToRender: String) {

    val forceDark = isSystemInDarkTheme()
    AndroidView(factory = {
        WebView(it).apply {
            settings.javaScriptEnabled = true
            settings.forceDark = if (forceDark) {
                WebSettings.FORCE_DARK_ON
            } else {
                WebSettings.FORCE_DARK_AUTO
            }
            webViewClient = WebViewClient()
            loadUrl(urlToRender)
        }
    })
}