package com.example.analytics.di

import com.example.analytics.data.repo.QueuedEventRepoImpl
import com.example.analytics.domain.Queue.repo.QueuedEventsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class AnalyticsModule {

    @Binds
    abstract fun getQueuedEventRepo(queuedEventRepoImpl: QueuedEventRepoImpl): QueuedEventsRepo


}