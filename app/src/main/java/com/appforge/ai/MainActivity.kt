package com.appforge.ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.appforge.ai.ui.navigation.AppNavigation
import com.appforge.ai.ui.theme.AppForgeAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppForgeAITheme {
                AppNavigation()
            }
        }
    }
}
