package com.appforge.ai.ui.screens.builder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appforge.ai.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuilderScreen(
    initialUrl: String,
    onBack: () -> Unit,
    onBuildRequested: (String, String, String) -> Unit
) {
    var appName by remember { mutableStateOf("My App") }
    var packageName by remember { mutableStateOf("com.example.app") }
    
    Scaffold(
        containerColor = DarkBackground,
        topBar = {
            TopAppBar(
                title = { Text("EASY BUILDER", color = TextPrimary) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkSurface)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Target URL", color = NeonCyan, fontSize = 14.sp)
            OutlinedTextField(
                value = initialUrl,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextSecondary
                )
            )
            
            Text("App Name", color = NeonCyan, fontSize = 14.sp)
            OutlinedTextField(
                value = appName,
                onValueChange = { appName = it },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary
                )
            )
            
            Text("Package Name", color = NeonCyan, fontSize = 14.sp)
            OutlinedTextField(
                value = packageName,
                onValueChange = { packageName = it },
                modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary
                )
            )
            
            Button(
                onClick = { onBuildRequested(initialUrl, appName, packageName) },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NeonPurple),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("PROCEED TO BUILD ENGINE", fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}
