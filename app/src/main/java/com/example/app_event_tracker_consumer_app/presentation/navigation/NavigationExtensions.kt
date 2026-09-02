package com.example.app_event_tracker_consumer_app.presentation.navigation

import androidx.navigation.NavHostController

fun NavHostController.navigateAndClearBackStack(route: AppRoutes) {
    this.navigate(route) {
        popUpTo(0) { inclusive = true }  // Clear entire back stack
        launchSingleTop = true
    }
}


fun NavHostController.navigateToRoute(route: AppRoutes) {
    this.navigate(route) {
        launchSingleTop = true
    }
}

fun NavHostController.navigateToPurchaseRoutes(route: PurchaseRoute) {
    this.navigate(route) {
        popUpTo(0) { inclusive = true }
        launchSingleTop = true
    }
}