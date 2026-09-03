package com.example.analytics.presentation.Queue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.analytics.domain.Queue.models.EventWithStatus

@Composable
fun QueuedEventsScreen(
    modifier: Modifier,
    events: List<EventWithStatus>
) {
    Column(
        modifier = modifier.fillMaxSize()
    ){
        Text(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            text = "Event Queue",
            textAlign = TextAlign.Start
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {

            items(events, key = { it.appEvent.id }) { eventWithStatus ->
                EventRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp),
                    eventWithStatus = eventWithStatus
                )
            }
        }
    }
}