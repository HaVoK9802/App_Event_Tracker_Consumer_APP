package com.example.analytics.domain.Queue.repo

import com.example.app_event_tracker.domain.models.AppEvent
import com.example.app_event_tracker.domain.models.AppEventWithStatus
import kotlinx.coroutines.flow.Flow

interface QueuedEventsRepo {

    suspend fun getProcessedEvents(): Flow<List<AppEvent>>

    suspend fun getUnprocessedEvents(): Flow<List<AppEventWithStatus>>
}