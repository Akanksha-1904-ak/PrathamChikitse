package com.pratham.chikitse.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pratham.chikitse.data.EmergencyDataSource
import com.pratham.chikitse.data.EmergencyType
import com.pratham.chikitse.ui.theme.PrathamTeal
import com.pratham.chikitse.ui.theme.PrathamTealDark

data class EmergencyContact(
    val name: String,
    val nameKn: String,
    val number: String,
    val color: Color,
    val emoji: String
)

val emergencyContacts = listOf(
    EmergencyContact("Ambulance", "ಆಂಬುಲೆನ್ಸ್", "108", Color(0xFFD32F2F), "🚑"),
    EmergencyContact("Police", "ಪೊಲೀಸ್", "100", Color(0xFF1565C0), "🚔"),
    EmergencyContact("Fire", "ಅಗ್ನಿಶಾಮಕ", "101", Color(0xFFE65100), "🔥"),
    EmergencyContact("Women", "ಮಹಿಳಾ", "1091", Color(0xFF6A1B9A), "👩"),
    EmergencyContact("Disaster", "ವಿಪತ್ತು", "1078", Color(0xFF37474F), "🆘"),
    EmergencyContact("Poison", "ವಿಷ", "1800-116-117", Color(0xFF2E7D32), "☠️")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    isKannada: Boolean,
    onLanguageToggle: () -> Unit,
    onEmergencyClick: (String) -> Unit,
    onHospitalClick: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            if (isKannada) "ಪ್ರಥಮ ಚಿಕಿತ್ಸೆ" else "Pratham Chikitse",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            if (isKannada) "ತುರ್ತು ಮಾರ್ಗದರ್ಶಿ" else "Emergency First Aid",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                },
                actions = {
                    Surface(
                        onClick = onLanguageToggle,
                        shape = RoundedCornerShape(20.dp),
                        color = Color.White.copy(alpha = 0.2f),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(
                            if (isKannada) "EN" else "ಕನ್ನಡ",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrathamTealDark
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF5F5F5))
        ) {
            // ✅ Emergency banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(listOf(PrathamTealDark, PrathamTeal))
                    )
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("⚡", fontSize = 20.sp)
                    Spacer(Modifier.width(10.dp))
                    Text(
                        if (isKannada) "ತುರ್ತು ಸ್ಥಿತಿ? ಒಂದು ಟ್ಯಾಪ್‌ನಲ್ಲಿ ಸಹಾಯ ಪಡೆಯಿರಿ"
                        else "Emergency? Get help with one tap",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(Modifier.height(10.dp))

            Text(
                if (isKannada) "ತುರ್ತು ಪ್ರಕಾರ ಆಯ್ಕೆ ಮಾಡಿ" else "Select Emergency Type",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color(0xFF212121),
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(8.dp))

            // ✅ Emergency grid — 3 columns
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(EmergencyDataSource.emergencies) { emergency ->
                    EmergencyCard(
                        emergency = emergency,
                        isKannada = isKannada,
                        onClick = { onEmergencyClick(emergency.id) }
                    )
                }
            }

            Spacer(Modifier.height(10.dp))

            // ✅ Emergency Numbers Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Icon(
                        Icons.Default.Phone,
                        contentDescription = null,
                        tint = Color(0xFFD32F2F),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        if (isKannada) "ತುರ್ತು ಸಂಪರ್ಕ ಸಂಖ್ಯೆಗಳು" else "Emergency Numbers",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF212121)
                    )
                }

                // ✅ 3 contacts per row — 2 rows total
                val rows = emergencyContacts.chunked(3)
                rows.forEach { rowContacts ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        rowContacts.forEach { contact ->
                            EmergencyCallCard(
                                contact = contact,
                                isKannada = isKannada,
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    val intent = Intent(
                                        Intent.ACTION_DIAL,
                                        Uri.parse("tel:${contact.number}")
                                    )
                                    context.startActivity(intent)
                                }
                            )
                        }
                        // Fill remaining slots if row has less than 3
                        repeat(3 - rowContacts.size) {
                            Spacer(Modifier.weight(1f))
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                }
            }

            // ✅ Find Hospital Button
            Button(
                onClick = onHospitalClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp)
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrathamTealDark),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    Icons.Default.LocalHospital,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    if (isKannada) "ಹತ್ತಿರದ ಆಸ್ಪತ್ರೆ ಹುಡುಕಿ" else "Find Nearby Hospital",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}

// ✅ Emergency call card
@Composable
fun EmergencyCallCard(
    contact: EmergencyContact,
    isKannada: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(72.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = contact.color.copy(alpha = 0.08f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(contact.emoji, fontSize = 18.sp)
            Spacer(Modifier.height(2.dp))
            Text(
                if (isKannada) contact.nameKn else contact.name,
                style = MaterialTheme.typography.labelSmall,
                color = contact.color,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                contact.number,
                style = MaterialTheme.typography.labelMedium,
                color = Color(0xFF212121),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// ✅ Emergency type card (unchanged)
@Composable
fun EmergencyCard(emergency: EmergencyType, isKannada: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .shadow(3.dp, RoundedCornerShape(14.dp))
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(14.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(emergency.color.copy(alpha = 0.12f), Color.White)
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .background(emergency.color)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(emergency.emoji, fontSize = 34.sp)
                Spacer(Modifier.height(6.dp))
                Text(
                    if (isKannada) emergency.titleKn else emergency.titleEn,
                    style = MaterialTheme.typography.labelLarge,
                    color = emergency.color,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}