// StepGuideScreen — swipeable step guide with TTS audio in Kannada and English
package com.pratham.chikitse.ui.screens

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pratham.chikitse.data.EmergencyDataSource
import com.pratham.chikitse.data.FirstAidStep
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun StepGuideScreen(
    emergencyId: String,
    isKannada: Boolean,
    onDosDonts: () -> Unit,
    onHospital: () -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val emergency = EmergencyDataSource.emergencies.first { it.id == emergencyId }
    val steps = EmergencyDataSource.steps[emergencyId] ?: emptyList()
    val pagerState = rememberPagerState(pageCount = { steps.size })
    val scope = rememberCoroutineScope()
    var audioEnabled by remember { mutableStateOf(false) }
    var tts by remember { mutableStateOf<TextToSpeech?>(null) }

    DisposableEffect(Unit) {
        var localTts: TextToSpeech? = null
        localTts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) tts = localTts
        }
        onDispose {
            localTts?.stop()
            localTts?.shutdown()
        }
    }

    fun speak(text: String) {
        tts?.let {
            it.language = if (isKannada) Locale("kn", "IN") else Locale.ENGLISH
            it.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    LaunchedEffect(pagerState.currentPage, audioEnabled) {
        if (audioEnabled && steps.isNotEmpty()) {
            val step = steps[pagerState.currentPage]
            speak(if (isKannada) step.descriptionKn else step.descriptionEn)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "${if (isKannada) "ಹಂತ" else "Step"} ${pagerState.currentPage + 1}/${steps.size}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Back", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        audioEnabled = !audioEnabled
                        if (!audioEnabled) tts?.stop()
                    }) {
                        Icon(
                            if (audioEnabled) Icons.Default.VolumeUp else Icons.Default.VolumeOff,
                            contentDescription = "Audio",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = emergency.color)
            )
        },
        bottomBar = {
            Column {
                // Dot indicators (replaces accompanist HorizontalPagerIndicator)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(steps.size) { index ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .size(if (pagerState.currentPage == index) 10.dp else 7.dp)
                                .background(
                                    color = if (pagerState.currentPage == index)
                                        emergency.color else Color.LightGray,
                                    shape = RoundedCornerShape(50)
                                )
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        },
                        enabled = pagerState.currentPage > 0,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                    ) {
                        Icon(Icons.Default.ChevronLeft, null)
                        Text(if (isKannada) "ಹಿಂದೆ" else "Prev")
                    }

                    Spacer(Modifier.width(8.dp))

                    if (pagerState.currentPage < steps.size - 1) {
                        Button(
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = emergency.color)
                        ) {
                            Text(if (isKannada) "ಮುಂದೆ" else "Next")
                            Icon(Icons.Default.ChevronRight, null)
                        }
                    } else {
                        Button(
                            onClick = onHospital,
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
                        ) {
                            Icon(Icons.Default.LocalHospital, null, tint = Color.White)
                            Spacer(Modifier.width(4.dp))
                            Text(
                                if (isKannada) "ಆಸ್ಪತ್ರೆ" else "Hospital",
                                color = Color.White
                            )
                        }
                    }
                }

                TextButton(
                    onClick = onDosDonts,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        if (isKannada) "ಮಾಡಬೇಕಾದ್ದು & ಮಾಡಬಾರದ್ದು ನೋಡಿ"
                        else "View Do's & Don'ts",
                        color = emergency.color
                    )
                }
            }
        }
    ) { padding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) { page ->
            StepCard(
                step = steps[page],
                isKannada = isKannada,
                accentColor = emergency.color
            )
        }
    }
}

@Composable
fun StepCard(step: FirstAidStep, isKannada: Boolean, accentColor: Color) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(accentColor, shape = RoundedCornerShape(28.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "${step.stepNumber}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = accentColor.copy(alpha = 0.1f)
            )
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(step.emoji, fontSize = 96.sp)
            }
        }

        Spacer(Modifier.height(20.dp))

        Text(
            if (isKannada) step.titleKn else step.titleEn,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = accentColor,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Text(
                if (isKannada) step.descriptionKn else step.descriptionEn,
                modifier = Modifier.padding(20.dp),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                lineHeight = 28.sp
            )
        }
    }
}