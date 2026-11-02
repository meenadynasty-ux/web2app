package com.appforge.ai.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast

class ApkDownloader(private val context: Context) {

    fun downloadApk(downloadUrl: String, appName: String, githubToken: String? = null) {
        try {
            val request = DownloadManager.Request(Uri.parse(downloadUrl)).apply {
                setTitle("$appName Build Complete")
                setDescription("Downloading native APK...")
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    "${appName.replace(" ", "_")}_Release.apk"
                )
                
                // If downloading directly from GitHub Artifacts API, authorization is required
                if (!githubToken.isNullOrEmpty()) {
                    addRequestHeader("Authorization", "Bearer $githubToken")
                }
                
                // Allow download over mobile data and Wi-Fi
                setAllowedOverMetered(true)
                setAllowedOverRoaming(true)
            }

            val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            downloadManager.enqueue(request)
            
            Toast.makeText(context, "Download started. Check notifications.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Download failed to initialize.", Toast.LENGTH_LONG).show()
        }
    }
}
