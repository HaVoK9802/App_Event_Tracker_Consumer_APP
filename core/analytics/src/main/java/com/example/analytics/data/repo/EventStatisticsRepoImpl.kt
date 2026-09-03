package com.example.analytics.data.repo

import com.example.analytics.domain.Statistics.repo.EventStatisticsRepo
import com.example.app_event_tracker.AppEventTracker
import com.example.app_event_tracker.domain.models.AppEventType
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class EventStatisticsRepoImpl @Inject constructor(
    private val appEventTracker: AppEventTracker
) : EventStatisticsRepo {
    override suspend fun getTotalProcessedEvents(): Int {
        return appEventTracker.getProcessedEvents().first().size
    }

    override suspend fun getTotalInstalls(): Int {
        return appEventTracker.getProcessedEvents().first().filter {
            it.appEventType.name == AppEventType.INSTALL
        }.size
    }

    override suspend fun getTotalCartItemsAdded(): Int {
        return appEventTracker.getProcessedEvents().first().filter {
            it.appEventType.name == AppEventType.ADD_TO_CART
        }.size
    }


    override suspend fun getTotalVisits(): Int {
        return appEventTracker.getProcessedEvents().first().filter {
            it.appEventType.name == AppEventType.VISIT
        }.size
    }

    override suspend fun getTotalUniqueScreenVisits(): Int {
        return appEventTracker.getProcessedEvents().first().filter {
            it.appEventType.name == AppEventType.SCREEN_VISIT
        }.size
    }

    override suspend fun getTotalItemPurchases(): Int {
        return appEventTracker.getProcessedEvents().first().filter {
            it.appEventType.name == AppEventType.PURCHASE
        }.size
    }
}