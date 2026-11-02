package com.appforge.ai.ui.screens.studio

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appforge.ai.data.AdvancedConfig
import com.appforge.ai.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedStudioScreen(
    onBack: () -> Unit,
    onSave: (AdvancedConfig) -> Unit
) {
    var userAgent by remember { mutableStateOf("") }
    var versionName by remember { mutableStateOf("1.0.0") }
    var enableCamera by remember { mutableStateOf(false) }
    var enableLocation by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = DarkBackground,
        topBar = {
            TopAppBar(
                title = { Text("ADVANCED STUDIO", color = TextPrimary, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkSurface)
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    onSave(
                        AdvancedConfig(
                            customUserAgent = userAgent,
                            versionName = versionName,
                            enableCamera = enableCamera,
                            enableLocation = enableLocation
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NeonPurple),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("SAVE CONFIGURATION", fontWeight = FontWeight.Bold, color = Color.White, letterSpacing = 1.sp)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Custom User Agent (Optional)", color = NeonCyan, fontSize = 14.sp)
            OutlinedTextField(
                value = userAgent,
                onValueChange = { userAgent = it },
                placeholder = { Text("Mozilla/5.0...", color = TextSecondary) },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 24.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary
                )
            )

            Text("Version Name", color = NeonCyan, fontSize = 14.sp)
            OutlinedTextField(
                value = versionName,
                onValueChange = { versionName = it },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 24.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary
                )
            )

            Text("Android Permissions", color = NeonCyan, fontSize = 14.sp, modifier = Modifier.padding(bottom = 16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Camera Access (WebRTC / Uploads)", color = TextPrimary)
                Switch(
                    checked = enableCamera,
                    onCheckedChange = { enableCamera = it },
                    colors = SwitchDefaults.colors(checkedThumbColor = NeonCyan, checkedTrackColor = NeonPurple)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Geolocation Access", color = TextPrimary)
                Switch(
                    checked = enableLocation,
                    onCheckedChange = { enableLocation = it },
                    colors = SwitchDefaults.colors(checkedThumbColor = NeonCyan, checkedTrackColor = NeonPurple)
                )
            }
        }
    }
}
