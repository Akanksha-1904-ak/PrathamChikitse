package com.pratham.chikitse.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pratham.chikitse.ui.screens.*

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Detail : Screen("detail/{emergencyId}") {
        fun createRoute(id: String) = "detail/$id"
    }
    object StepGuide : Screen("steps/{emergencyId}") {
        fun createRoute(id: String) = "steps/$id"
    }
    object DosDonts : Screen("dosdOnts/{emergencyId}") {
        fun createRoute(id: String) = "dosdOnts/$id"
    }
    object HospitalFinder : Screen("hospitals")
}

@Composable
fun NavGraph(navController: NavHostController, isKannada: Boolean, onLanguageToggle: () -> Unit) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(onFinished = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }
        composable(Screen.Home.route) {
            HomeScreen(
                isKannada = isKannada,
                onLanguageToggle = onLanguageToggle,
                onEmergencyClick = { id -> navController.navigate(Screen.Detail.createRoute(id)) },
                onHospitalClick = { navController.navigate(Screen.HospitalFinder.route) }
            )
        }
        composable(
            Screen.Detail.route,
            arguments = listOf(navArgument("emergencyId") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("emergencyId") ?: return@composable
            EmergencyDetailScreen(
                emergencyId = id,
                isKannada = isKannada,
                onStartGuide = { navController.navigate(Screen.StepGuide.createRoute(id)) },
                onDosDonts = { navController.navigate(Screen.DosDonts.createRoute(id)) },
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            Screen.StepGuide.route,
            arguments = listOf(navArgument("emergencyId") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("emergencyId") ?: return@composable
            StepGuideScreen(
                emergencyId = id,
                isKannada = isKannada,
                onDosDonts = { navController.navigate(Screen.DosDonts.createRoute(id)) },
                onHospital = { navController.navigate(Screen.HospitalFinder.route) },
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            Screen.DosDonts.route,
            arguments = listOf(navArgument("emergencyId") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("emergencyId") ?: return@composable
            DosDontsScreen(
                emergencyId = id,
                isKannada = isKannada,
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.HospitalFinder.route) {
            HospitalFinderScreen(
                isKannada = isKannada,
                onBack = { navController.popBackStack() }
            )
        }
    }
}