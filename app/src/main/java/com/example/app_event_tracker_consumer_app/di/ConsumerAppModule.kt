package com.example.app_event_tracker_consumer_app.di

import android.app.Application
import com.example.app_event_tracker.AppEventTracker
import com.example.app_event_tracker_consumer_app.ConsumerApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConsumerAppModule {

    @Provides
    @Singleton
    fun providesAppEventAnalytics(
        application: Application
    ): AppEventTracker {
        return (application as ConsumerApplication).appEventTracker
    }
}