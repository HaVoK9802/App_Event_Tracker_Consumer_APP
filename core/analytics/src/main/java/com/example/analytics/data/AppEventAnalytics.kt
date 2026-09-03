package com.example.analytics.data

import com.example.app_event_tracker.AppEventTracker

interface AppEventAnalytics {
    val appEventTracker: AppEventTracker
}