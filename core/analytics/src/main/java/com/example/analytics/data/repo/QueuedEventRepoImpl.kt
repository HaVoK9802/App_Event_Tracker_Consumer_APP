package com.example.analytics.data.repo

import com.example.analytics.domain.Queue.repo.QueuedEventsRepo
import com.example.app_event_tracker.AppEventTracker
import com.example.app_event_tracker.domain.models.AppEvent
import com.example.app_event_tracker.domain.models.AppEventWithStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QueuedEventRepoImpl @Inject constructor(
    private val appEventTracker: AppEventTracker
): QueuedEventsRepo {



    override suspend fun getProcessedEvents(): Flow<List<AppEvent>> {
        return appEventTracker.getProcessedEvents()
    }

    override suspend fun getUnprocessedEvents(): Flow<List<AppEventWithStatus>> {
        return appEventTracker.getUnprocessedEvents()
    }

}