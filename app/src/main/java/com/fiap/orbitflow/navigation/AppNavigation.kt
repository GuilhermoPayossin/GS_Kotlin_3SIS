package com.fiap.orbitflow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fiap.orbitflow.ui.screens.DashboardScreen
import com.fiap.orbitflow.ui.screens.HomeScreen
import com.fiap.orbitflow.ui.screens.ObjectsScreen
import com.fiap.orbitflow.ui.screens.RiskZonesScreen

object OrbitRoutes {
    const val Home = "home"
    const val Dashboard = "dashboard"
    const val Objects = "objects"
    const val RiskZones = "risk_zones"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = OrbitRoutes.Home
    ) {
        composable(OrbitRoutes.Home) {
            HomeScreen(
                onDashboardClick = { navController.navigateSingleTop(OrbitRoutes.Dashboard) },
                onObjectsClick = { navController.navigateSingleTop(OrbitRoutes.Objects) },
                onRiskZonesClick = { navController.navigateSingleTop(OrbitRoutes.RiskZones) }
            )
        }

        composable(OrbitRoutes.Dashboard) {
            DashboardScreen(
                onBack = { navController.goBackOrHome() },
                onHomeClick = { navController.navigateSingleTop(OrbitRoutes.Home) },
                onObjectsClick = { navController.navigateSingleTop(OrbitRoutes.Objects) },
                onRiskZonesClick = { navController.navigateSingleTop(OrbitRoutes.RiskZones) }
            )
        }

        composable(OrbitRoutes.Objects) {
            ObjectsScreen(
                onBack = { navController.goBackOrHome() },
                onDashboardClick = { navController.navigateSingleTop(OrbitRoutes.Dashboard) },
                onRiskZonesClick = { navController.navigateSingleTop(OrbitRoutes.RiskZones) }
            )
        }

        composable(OrbitRoutes.RiskZones) {
            RiskZonesScreen(
                onBack = { navController.goBackOrHome() },
                onDashboardClick = { navController.navigateSingleTop(OrbitRoutes.Dashboard) },
                onObjectsClick = { navController.navigateSingleTop(OrbitRoutes.Objects) }
            )
        }
    }
}

private fun NavHostController.navigateSingleTop(route: String) {
    navigate(route) {
        launchSingleTop = true
        restoreState = true
    }
}

private fun NavHostController.goBackOrHome() {
    if (!popBackStack()) {
        navigateSingleTop(OrbitRoutes.Home)
    }
}
