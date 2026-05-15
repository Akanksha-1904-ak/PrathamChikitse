// DosDontsScreen — color coded Do's and Don'ts per emergency type
package com.pratham.chikitse.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pratham.chikitse.data.DosDontsItem
import com.pratham.chikitse.data.EmergencyDataSource
import com.pratham.chikitse.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DosDontsScreen(emergencyId: String, isKannada: Boolean, onBack: () -> Unit) {
    val emergency = EmergencyDataSource.emergencies.first { it.id == emergencyId }
    val items = EmergencyDataSource.dosDonts[emergencyId] ?: emptyList()
    val dos = items.filter { it.isDo }
    val donts = items.filter { !it.isDo }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (isKannada) "ಮಾಡಬೇಕಾದ್ದು & ಮಾಡಬಾರದ್ದು" else "Do's & Don'ts",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = emergency.color)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF5F5F5)),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text(
                    "✅ ${if (isKannada) "ಮಾಡಬೇಕಾದ್ದು" else "Do's"}",
                    style = MaterialTheme.typography.titleLarge,
                    color = DoGreen,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            items(dos) { item ->
                DosDontsCard(item = item, isKannada = isKannada)
            }
            item { Spacer(Modifier.height(8.dp)) }
            item {
                Text(
                    "❌ ${if (isKannada) "ಮಾಡಬಾರದ್ದು" else "Don'ts"}",
                    style = MaterialTheme.typography.titleLarge,
                    color = DontRed,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            items(donts) { item ->
                DosDontsCard(item = item, isKannada = isKannada)
            }
        }
    }
}

@Composable
fun DosDontsCard(item: DosDontsItem, isKannada: Boolean) {
    val bgColor = if (item.isDo) DoGreenBg else DontRedBg
    val textColor = if (item.isDo) DoGreen else DontRed
    val icon = if (item.isDo) Icons.Default.CheckCircle else Icons.Default.Cancel

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = textColor, modifier = Modifier.size(28.dp))
            Spacer(Modifier.width(12.dp))
            Text(
                if (isKannada) item.textKn else item.textEn,
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF212121)
            )
        }
    }
}