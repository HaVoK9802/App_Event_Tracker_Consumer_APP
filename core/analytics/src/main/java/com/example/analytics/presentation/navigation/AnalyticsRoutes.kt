package com.example.analytics.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AnalyticsRoutes {

    @Serializable
    data object QueuedEvents : AnalyticsRoutes


    @Serializable
    data object Statistics: AnalyticsRoutes

}
