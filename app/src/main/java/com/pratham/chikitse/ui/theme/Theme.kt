package com.pratham.chikitse.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = PrathamTeal,
    onPrimary = Color.White,
    primaryContainer = PrathamTealLight,
    secondary = PrathamRed,
    onSecondary = Color.White,
    background = SurfaceLight,
    surface = CardBg,
    error = DontRed
)

@Composable
fun PrathamChikitseTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}