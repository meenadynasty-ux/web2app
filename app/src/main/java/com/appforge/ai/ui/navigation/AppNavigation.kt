package com.appforge.ai.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appforge.ai.ui.screens.home.HomeScreen
import com.appforge.ai.ui.screens.builder.BuilderScreen
import com.appforge.ai.ui.screens.builder.BuildProgressScreen
import com.appforge.ai.ui.screens.history.HistoryScreen
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onNavigateToBuilder = { url ->
                    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                    navController.navigate("builder/$encodedUrl")
                }
            )
            // Note: Add a button in HomeScreen to navigate to "history" route
        }
        
        composable("builder/{url}") { backStackEntry ->
            val encodedUrl = backStackEntry.arguments?.getString("url") ?: ""
            val url = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())
            
            BuilderScreen(
                initialUrl = url,
                onBack = { navController.popBackStack() },
                onBuildRequested = { targetUrl, appName, packageName ->
                    val encUrl = URLEncoder.encode(targetUrl, StandardCharsets.UTF_8.toString())
                    navController.navigate("progress/$appName/$packageName/$encUrl")
                }
            )
        }
        
        composable("progress/{appName}/{packageName}/{url}") { backStackEntry ->
            val appName = backStackEntry.arguments?.getString("appName") ?: "App"
            val packageName = backStackEntry.arguments?.getString("packageName") ?: "com.example"
            val encodedUrl = backStackEntry.arguments?.getString("url") ?: ""
            val url = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())
            
            BuildProgressScreen(appName = appName, packageName = packageName, url = url)
        }
        
        composable("history") {
            // Mock empty list for MVP, in reality pass Flow from Room DB via ViewModel
            HistoryScreen(
                historyList = emptyList(),
                onBack = { navController.popBackStack() },
                onDownloadClick = { url, name -> /* Trigger ApkDownloader */ }
            )
        }
    }
}
