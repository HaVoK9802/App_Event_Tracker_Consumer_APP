package com.example.app_event_tracker_consumer_app.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoutes {

    @Serializable
    data object LoginScreen : AppRoutes

    @Serializable
    data object ShoppingScreen: AppRoutes

    @Serializable
    data object ItemCartScreen: AppRoutes

    @Serializable
    data object PurchaseGraph: AppRoutes

}

@Serializable
sealed interface PurchaseRoute {

    @Serializable
    data object Screen1 : PurchaseRoute

    @Serializable
    data object Screen2 : PurchaseRoute
}