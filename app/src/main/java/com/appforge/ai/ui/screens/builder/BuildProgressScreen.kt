package com.appforge.ai.ui.screens.builder

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appforge.ai.data.AppConfig
import com.appforge.ai.ui.theme.*
import com.appforge.ai.viewmodel.BuildViewModel

@Composable
fun BuildProgressScreen(appName: String, packageName: String, url: String) {
    val viewModel: BuildViewModel = viewModel()
    val status by viewModel.buildStatus.collectAsState()
    
    // Auto-start the build process when screen opens
    LaunchedEffect(Unit) {
        val config = AppConfig(url = url, appName = appName, packageName = packageName)
        // REPLACE WITH YOUR ACTUAL GITHUB DETAILS FOR TESTING
        viewModel.startNativeBuild(config, "YOUR_GITHUB_TOKEN", "YOUR_GITHUB_USERNAME", "YOUR_REPO_NAME")
    }

    Surface(modifier = Modifier.fillMaxSize(), color = DarkBackground) {
        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (status == "COMPLETED") {
                Icon(Icons.Filled.CheckCircle, contentDescription = "Done", tint = NeonCyan, modifier = Modifier.size(64.dp))
            } else {
                CircularProgressIndicator(color = NeonCyan, modifier = Modifier.size(64.dp), strokeWidth = 6.dp)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                text = status,
                color = if (status == "COMPLETED") NeonCyan else NeonPurple,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Target: $appName\nPackage: $packageName",
                color = TextSecondary,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
