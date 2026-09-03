package com.example.analytics.domain.Queue.models

import com.example.app_event_tracker.domain.models.AppEvent

data class EventWithStatus(
    val appEvent: AppEvent,
    val status: String? = "Processed",
    val retryAt: Long? = null,
    val retryAttempt: Int? = null
)