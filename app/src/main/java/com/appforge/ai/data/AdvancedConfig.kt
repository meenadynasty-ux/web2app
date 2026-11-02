package com.appforge.ai.data

data class AdvancedConfig(
    val customUserAgent: String = "",
    val orientation: String = "Sensor (Auto)",
    val versionCode: Int = 1,
    val versionName: String = "1.0.0",
    val enableCamera: Boolean = false,
    val enableLocation: Boolean = false
)
