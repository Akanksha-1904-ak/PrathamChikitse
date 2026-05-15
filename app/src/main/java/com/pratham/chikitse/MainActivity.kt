// MainActivity — entry point with language toggle and Compose setup
package com.pratham.chikitse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.pratham.chikitse.navigation.NavGraph
import com.pratham.chikitse.ui.theme.PrathamChikitseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrathamChikitseTheme {
                val navController = rememberNavController()
                var isKannada by remember { mutableStateOf(false) }
                NavGraph(
                    navController = navController,
                    isKannada = isKannada,
                    onLanguageToggle = { isKannada = !isKannada }
                )
            }
        }
    }
}