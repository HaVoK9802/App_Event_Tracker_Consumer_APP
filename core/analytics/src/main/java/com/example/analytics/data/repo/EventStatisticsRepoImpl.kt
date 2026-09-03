package com.example.analytics.data.repo

import com.example.analytics.domain.Statistics.repo.EventStatisticsRepo
import com.example.app_event_tracker.AppEventTracker
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class EventStatisticsRepoImpl @Inject constructor(
    private val appEventTracker: AppEventTracker
): EventStatisticsRepo{
    override suspend fun getTotalProcessedEvents(): Int {
        return appEventTracker.getProcessedEvents().first().size
    }

    override suspend fun getTotalInstalls(): Int {
        return appEventTracker.getProcessedEvents().first().filter {
            it.appEventType.name == "Install"
        }.size
    }

    override suspend fun getTotalCartItemsAdded(): Int {
        return appEventTracker.getProcessedEvents().first().filter {
            it.appEventType.name == "AddToCart"
        }.size    }

    override suspend fun getTotalVisits(): Int {
        return appEventTracker.getProcessedEvents().first().filter {
            it.appEventType.name == "Visit"
        }.size    }

    override suspend fun getTotalItemPurchases(): Int {
        return appEventTracker.getProcessedEvents().first().filter {
            it.appEventType.name == "Purchase"
        }.size    }
}