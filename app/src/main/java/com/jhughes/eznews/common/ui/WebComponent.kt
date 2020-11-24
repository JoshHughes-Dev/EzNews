package com.jhughes.eznews.common.ui

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebComponent(
    modifier: Modifier = Modifier,
    urlToRender: String
) {
    AndroidView(viewBlock = ::WebView) { webView ->
        with(webView) {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(urlToRender)
        }
    }
}