package com.appforge.ai.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.appforge.ai.data.AppConfig
import com.appforge.ai.data.GitHubBuildEngine
import com.appforge.ai.data.history.AppDatabase
import com.appforge.ai.data.history.BuildHistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BuildViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val buildEngine = GitHubBuildEngine()
    
    private val _buildStatus = MutableStateFlow("INITIALIZING")
    val buildStatus: StateFlow<String> = _buildStatus

    private val _downloadUrl = MutableStateFlow<String?>(null)
    val downloadUrl: StateFlow<String?> = _downloadUrl

    fun startNativeBuild(config: AppConfig, githubToken: String, owner: String, repo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _buildStatus.value = "STARTING ENGINE..."
            
            val success = buildEngine.triggerBuild(config, githubToken, owner, repo)
            if (success) {
                _buildStatus.value = "BUILD IN PROGRESS"
                pollBuildStatus(config, githubToken, owner, repo)
            } else {
                _buildStatus.value = "FAILED TO START"
            }
        }
    }

    private suspend fun pollBuildStatus(config: AppConfig, token: String, owner: String, repo: String) {
        var isCompleted = false
        var attempts = 0
        val maxAttempts = 30 // 5 minutes (10s intervals)

        while (!isCompleted && attempts < maxAttempts) {
            delay(10000) // Wait 10 seconds between checks
            attempts++
            
            // Note: In a production app, you would query the GitHub API /actions/runs endpoint here.
            // For this MVP, we simulate the polling success after 30 seconds (3 attempts)
            // Replace this block with actual HttpURLConnection to fetch artifact URL.
            
            if (attempts >= 3) {
                isCompleted = true
                val artifactUrl = "https://github.com/$owner/$repo/actions/runs/demo-artifact.zip"
                
                _buildStatus.value = "COMPLETED"
                _downloadUrl.value = artifactUrl
                
                // Save to Room DB
                val history = BuildHistoryEntity(
                    appName = config.appName,
                    packageName = config.packageName,
                    timestamp = System.currentTimeMillis(),
                    downloadUrl = artifactUrl,
                    status = "COMPLETED"
                )
                db.buildHistoryDao().insertBuild(history)
            }
        }
        
        if (!isCompleted) {
            _buildStatus.value = "TIMEOUT"
        }
    }
}
