package com.pratham.chikitse.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pratham.chikitse.data.EmergencyDataSource
import com.pratham.chikitse.ui.theme.PrathamTeal
import com.pratham.chikitse.ui.theme.PrathamTealDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyDetailScreen(
    emergencyId: String,
    isKannada: Boolean,
    onStartGuide: () -> Unit,
    onDosDonts: () -> Unit,
    onBack: () -> Unit
) {
    val emergency = EmergencyDataSource.emergencies.first { it.id == emergencyId }
    val steps = EmergencyDataSource.steps[emergencyId] ?: emptyList()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (isKannada) emergency.titleKn else emergency.titleEn,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = emergency.color)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Brush.verticalGradient(listOf(emergency.color, emergency.color.copy(alpha = 0.7f))))
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(emergency.emoji, fontSize = 64.sp)
                    Spacer(Modifier.height(12.dp))
                    Text(
                        if (isKannada) emergency.descriptionKn else emergency.descriptionEn,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // Steps preview
            Text(
                if (isKannada) "${steps.size} ಹಂತಗಳು" else "${steps.size} Steps",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(8.dp))

            steps.take(3).forEach { step ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(emergency.color, shape = RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("${step.stepNumber}", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                    Spacer(Modifier.width(12.dp))
                    Text(
                        if (isKannada) step.titleKn else step.titleEn,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            if (steps.size > 3) {
                Text(
                    if (isKannada) "... ಮತ್ತು ${steps.size - 3} ಹೆಚ್ಚು"
                    else "... and ${steps.size - 3} more",
                    modifier = Modifier.padding(start = 60.dp, top = 4.dp),
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(Modifier.height(24.dp))

            // Action Buttons
            Button(
                onClick = onStartGuide,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = emergency.color),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    if (isKannada) "ಮಾರ್ಗದರ್ಶಿ ಪ್ರಾರಂಭಿಸಿ →" else "Start Guide →",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }

            Spacer(Modifier.height(12.dp))

            OutlinedButton(
                onClick = onDosDonts,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = emergency.color)
            ) {
                Icon(Icons.Default.CheckCircle, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(
                    if (isKannada) "ಮಾಡಬೇಕಾದ್ದು ಮತ್ತು ಮಾಡಬಾರದ್ದು" else "Do's & Don'ts",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}