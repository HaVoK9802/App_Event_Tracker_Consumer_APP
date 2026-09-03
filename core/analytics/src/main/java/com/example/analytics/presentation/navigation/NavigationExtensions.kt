package com.example.analytics.presentation.navigation

import androidx.navigation.NavHostController

fun NavHostController.navigateAndClearBackStack(route: AnalyticsRoutes) {
    this.navigate(route) {
        popUpTo(0) { inclusive = true }  // Clear entire back stack
        launchSingleTop = true
    }
}


fun NavHostController.navigateToRoute(route: AnalyticsRoutes) {
    this.navigate(route) {
        launchSingleTop = true
    }
}

fun NavHostController.navigateToPurchaseRoutes(route: AnalyticsRoutes) {
    this.navigate(route) {
        popUpTo(0) { inclusive = true }
        launchSingleTop = true
    }
}