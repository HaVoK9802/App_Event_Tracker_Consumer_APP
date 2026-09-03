package com.example.analytics.domain.Statistics.models



data class CollectiveEventStatistics(
    val totalProcessedEvents: EventStat? = null,
    val installs: EventStat? = null,
    val visits: EventStat? = null,
    val itemsAddedToCart: EventStat? = null,
    val purchases: EventStat? = null
)


data class EventStat(
    val count: Int,
    val percentage: Float
)