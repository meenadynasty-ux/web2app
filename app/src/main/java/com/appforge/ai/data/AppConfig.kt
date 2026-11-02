package com.appforge.ai.data

data class AppConfig(
    val url: String = "",
    val appName: String = "",
    val packageName: String = "",
    val themeColor: String = "#00F0FF",
    val enablePullToRefresh: Boolean = true,
    val enableJavaScript: Boolean = true
)
