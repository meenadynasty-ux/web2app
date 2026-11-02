package com.appforge.ai.ui.components

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun AdvancedWebView(
    url: String,
    modifier: Modifier = Modifier,
    enableJavaScript: Boolean = true
) {
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                layoutParams = android.view.ViewGroup.LayoutParams(
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT
                )
                
                // Set custom clients for handling links and progress
                webViewClient = WebViewClient()
                webChromeClient = WebChromeClient()
                
                settings.apply {
                    javaScriptEnabled = enableJavaScript
                    domStorageEnabled = true
                    useWideViewPort = true
                    loadWithOverviewMode = true
                    setSupportZoom(true)
                    builtInZoomControls = true
                    displayZoomControls = false
                    mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
                }
                
                loadUrl(url)
            }
        },
        update = { webView ->
            // Update URL if it changes from state
            if (webView.url != url) {
                webView.loadUrl(url)
            }
        }
    )
}
