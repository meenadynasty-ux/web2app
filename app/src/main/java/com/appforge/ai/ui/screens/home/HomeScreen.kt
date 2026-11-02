package com.appforge.ai.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appforge.ai.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToBuilder: (String) -> Unit
) {
    var urlInput by remember { mutableStateOf("") }
    
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(NeonCyan, NeonPurple)
    )

    Scaffold(
        containerColor = DarkBackground,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Open History or Studio */ },
                containerColor = NeonPurple,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "New Advanced Build")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            
            Text(
                text = "APPFORGE AI",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    color = TextPrimary
                )
            )
            
            Text(
                text = "NATIVE ENGINE ACTIVE",
                style = MaterialTheme.typography.labelMedium.copy(
                    color = NeonCyan,
                    letterSpacing = 4.sp
                )
            )
            
            Spacer(modifier = Modifier.height(64.dp))
            
            // Glassmorphic Input Area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(GlassWhite)
                    .border(1.dp, gradientBrush, RoundedCornerShape(16.dp))
                    .padding(4.dp)
            ) {
                OutlinedTextField(
                    value = urlInput,
                    onValueChange = { urlInput = it },
                    placeholder = { Text("https://your-website.com", color = TextSecondary) },
                    leadingIcon = {
                        Icon(Icons.Filled.Language, contentDescription = "URL", tint = NeonCyan)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        cursorColor = NeonCyan,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    ),
                    singleLine = true
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Glowing Action Button
            Button(
                onClick = { 
                    if (urlInput.isNotBlank()) onNavigateToBuilder(urlInput) 
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(gradientBrush, RoundedCornerShape(28.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "INITIALIZE BUILD",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    letterSpacing = 1.sp
                )
            }
            
            Spacer(modifier = Modifier.height(48.dp))
            
            // Recent Builds Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recent Builds",
                    color = TextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                TextButton(onClick = { /* View All */ }) {
                    Text("View All", color = NeonCyan)
                }
            }
        }
    }
}
