package com.appforge.ai.ui.screens.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.appforge.ai.ui.components.AdvancedWebView
import com.appforge.ai.ui.theme.DarkBackground
import com.appforge.ai.ui.theme.DarkSurface
import com.appforge.ai.ui.theme.TextPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewScreen(
    url: String,
    onBack: () -> Unit
) {
    Scaffold(
        containerColor = DarkBackground,
        topBar = {
            TopAppBar(
                title = { Text("LIVE PREVIEW", color = TextPrimary) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkSurface),
                navigationIcon = {
                    // Back button integration goes here
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AdvancedWebView(
                url = url,
                modifier = Modifier.fillMaxSize(),
                enableJavaScript = true
            )
        }
    }
}
