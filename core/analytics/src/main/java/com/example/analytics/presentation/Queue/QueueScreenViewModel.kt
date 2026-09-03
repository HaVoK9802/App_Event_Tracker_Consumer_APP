package com.example.analytics.presentation.Queue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.analytics.domain.Queue.models.EventWithStatus
import com.example.analytics.domain.Queue.repo.QueuedEventsRepo
import com.example.app_event_tracker.domain.models.UploadStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class QueueScreenViewModel @Inject constructor(
    private val queuedEventsRepo: QueuedEventsRepo
) : ViewModel() {


    val eventsList: StateFlow<List<EventWithStatus>> =
        combine(
            flow {
                emitAll(queuedEventsRepo.getProcessedEvents())
            },
            flow {
                emitAll(queuedEventsRepo.getUnprocessedEvents())
            }
        ) { processed, unprocessed ->

            val processedEvents = processed.map {
                EventWithStatus(
                    appEvent = it,
                    status = "Processed"
                )
            }

            val unprocessedEvents = unprocessed.map {
                EventWithStatus(
                    appEvent = it.appEvent,
                    status = when (it.uploadStatus.uploadStatus) {
                        UploadStatus.FAILED -> null
                        UploadStatus.QUEUED -> "Queued"
                        UploadStatus.PROCESSING,
                        UploadStatus.RETRYING -> "Processing"
                    },
                    retryAt = it.uploadStatus.retryAt,
                    retryAttempt = it.uploadStatus.retryAttempt
                )
            }

            (processedEvents + unprocessedEvents)
                .sortedByDescending { it.appEvent.timestamp }

        }.flowOn(Dispatchers.IO)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = emptyList()
            )

}

