package com.example.app_event_tracker_consumer_app

import android.app.Application
import com.example.analytics.data.AppEventAnalytics
import com.example.app_event_tracker.AppEventTracker
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ConsumerApplication: Application(), AppEventAnalytics {

    override lateinit var appEventTracker: AppEventTracker


    override fun onCreate() {
        super.onCreate()
        AppEventTracker.initializeTracker(applicationContext)
        appEventTracker = AppEventTracker.getInstance()
    }
}