package com.example.analytics.domain.Statistics.repo

interface EventStatisticsRepo {

    suspend fun getTotalProcessedEvents(): Int

    suspend fun getTotalInstalls(): Int

    suspend fun getTotalCartItemsAdded(): Int

    suspend fun getTotalVisits(): Int

    suspend fun getTotalUniqueScreenVisits(): Int

    suspend fun getTotalItemPurchases(): Int


}