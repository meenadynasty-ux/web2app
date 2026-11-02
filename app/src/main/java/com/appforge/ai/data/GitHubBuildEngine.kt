package com.appforge.ai.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONObject

class GitHubBuildEngine {
    // SECURITY NOTE: In production, NEVER hardcode your GitHub Token in the APK.
    // This should ideally route through a secure backend, but for the MVP we call it directly.
    
    suspend fun triggerBuild(config: AppConfig, githubToken: String, owner: String, repo: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val urlString = "https://api.github.com/repos/$owner/$repo/actions/workflows/build-apk.yml/dispatches"
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            
            connection.requestMethod = "POST"
            connection.setRequestProperty("Authorization", "Bearer $githubToken")
            connection.setRequestProperty("Accept", "application/vnd.github.v3+json")
            connection.setRequestProperty("Content-Type", "application/json")
            connection.doOutput = true

            val jsonPayload = JSONObject().apply {
                put("ref", "main")
                put("inputs", JSONObject().apply {
                    put("WEBSITE_URL", config.url)
                    put("APP_NAME", config.appName)
                    put("PACKAGE_NAME", config.packageName)
                    put("THEME_COLOR", config.themeColor)
                })
            }.toString()

            connection.outputStream.use { os ->
                val input = jsonPayload.toByteArray(Charsets.UTF_8)
                os.write(input, 0, input.size)
            }

            val responseCode = connection.responseCode
            return@withContext responseCode in 200..299
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext false
        }
    }
}
