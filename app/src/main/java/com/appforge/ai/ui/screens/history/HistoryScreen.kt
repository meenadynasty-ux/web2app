package com.appforge.ai.ui.screens.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appforge.ai.data.history.BuildHistoryEntity
import com.appforge.ai.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    historyList: List<BuildHistoryEntity>,
    onBack: () -> Unit,
    onDownloadClick: (String, String) -> Unit
) {
    Scaffold(
        containerColor = DarkBackground,
        topBar = {
            TopAppBar(
                title = { Text("BUILD HISTORY", color = TextPrimary, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkSurface)
            )
        }
    ) { paddingValues ->
        if (historyList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                Text("No past builds found.", color = TextSecondary)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(historyList) { build ->
                    BuildHistoryItem(build, onDownloadClick)
                }
            }
        }
    }
}

@Composable
fun BuildHistoryItem(
    build: BuildHistoryEntity,
    onDownloadClick: (String, String) -> Unit
) {
    val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
    val dateString = dateFormat.format(Date(build.timestamp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(DarkSurface)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = build.appName, color = NeonCyan, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = build.packageName, color = TextSecondary, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = dateString, color = TextPrimary, fontSize = 12.sp)
        }
        
        if (build.status == "COMPLETED") {
            IconButton(onClick = { onDownloadClick(build.downloadUrl, build.appName) }) {
                Icon(Icons.Filled.Download, contentDescription = "Download APK", tint = NeonPurple)
            }
        } else {
            Text(text = build.status, color = Color.Yellow, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}
