package com.appforge.ai.ui.screens.assets

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appforge.ai.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssetEditorScreen(
    onBack: () -> Unit,
    onSave: (Uri?, Uri?) -> Unit
) {
    var iconUri by remember { mutableStateOf<Uri?>(null) }
    var splashUri by remember { mutableStateOf<Uri?>(null) }

    val iconPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> iconUri = uri }

    val splashPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> splashUri = uri }

    Scaffold(
        containerColor = DarkBackground,
        topBar = {
            TopAppBar(
                title = { Text("BRANDING ASSETS", color = TextPrimary, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkSurface)
            )
        },
        bottomBar = {
            Button(
                onClick = { onSave(iconUri, splashUri) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NeonPurple),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("CONFIRM ASSETS", fontWeight = FontWeight.Bold, color = Color.White, letterSpacing = 1.sp)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("App Icon (512x512 PNG recommended)", color = NeonCyan, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(12.dp))
            
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(DarkSurface)
                    .border(1.dp, if (iconUri != null) NeonCyan else Color.Transparent, RoundedCornerShape(16.dp))
                    .clickable { iconPickerLauncher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (iconUri != null) {
                    Text("Icon Selected", color = NeonCyan, fontSize = 12.sp)
                } else {
                    Icon(Icons.Filled.Image, contentDescription = "Pick Icon", tint = TextSecondary, modifier = Modifier.size(40.dp))
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            Text("Splash Screen (1080x1920 PNG/JPG)", color = NeonCyan, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(210.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(DarkSurface)
                    .border(1.dp, if (splashUri != null) NeonPurple else Color.Transparent, RoundedCornerShape(16.dp))
                    .clickable { splashPickerLauncher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (splashUri != null) {
                    Text("Splash Selected", color = NeonPurple, fontSize = 12.sp)
                } else {
                    Icon(Icons.Filled.Image, contentDescription = "Pick Splash", tint = TextSecondary, modifier = Modifier.size(40.dp))
                }
            }
        }
    }
}
