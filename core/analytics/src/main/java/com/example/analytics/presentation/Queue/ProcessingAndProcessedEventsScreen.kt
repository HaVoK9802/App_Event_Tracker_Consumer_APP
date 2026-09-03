package com.example.analytics.presentation.Queue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.analytics.domain.Queue.models.EventWithStatus

@Composable
fun ProcessingAndProcessedEventsScreen(
    modifier: Modifier,
    events: List<EventWithStatus>
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(events, key = {it.appEvent.id}){ eventWithStatus ->
           EventRow(
               modifier = Modifier.fillMaxWidth().height(72.dp),
               eventWithStatus = eventWithStatus
           )
        }
    }
}