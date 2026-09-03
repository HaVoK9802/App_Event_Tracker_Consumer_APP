package com.example.analytics.presentation.Queue

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.analytics.domain.Queue.models.EventWithStatus
import com.example.analytics.domain.mappers.toColor
import com.example.analytics.domain.mappers.toTimeStampPretty
import com.example.app_event_tracker.domain.models.AppEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun EventRow(
    modifier: Modifier,
    eventWithStatus: EventWithStatus
) {
    var now by remember {
        mutableLongStateOf(System.currentTimeMillis())
    }

    var retryingIn: String? by remember {
        mutableStateOf(null)
    }

    if (eventWithStatus.retryAt != null) {
        LaunchedEffect(eventWithStatus.retryAt) {
            while (isActive) {
                now = System.currentTimeMillis()

                val remainingMillis = eventWithStatus.retryAt - now
                val remainingSeconds =
                    (remainingMillis / 1_000L).coerceAtLeast(0L)

                retryingIn = if (remainingSeconds > 0L) {
                    "(Attempt: ${eventWithStatus.retryAttempt}) - Retrying in ${remainingSeconds}s"
                } else {
                    null
                }

                if (retryingIn == null) {
                    break
                }

                delay(1_000L.milliseconds)
            }
        }


    }

    val eventStatus = remember(eventWithStatus.status,retryingIn) {
        when (eventWithStatus.status) {
            null -> {
                EventStatus(
                    status = retryingIn ?: "Queued for Retry",
                    color = Color(0xFFFFC107)
                )
            }

            "Queued" -> {
                EventStatus(
                    status = "Queued",
                    color = Color(0xFFFFC107)
                )
            }

            "Processing" -> {
                EventStatus(
                    status = "Processing",
                    color = Color(0xFF0038FF)
                )
            }

            else -> {
                EventStatus(
                    status = "Processed",
                    color = Color(0xFF357C2B)
                )
            }
        }
    }

    EventRowTemplate(
        modifier = modifier,
        appEvent = eventWithStatus.appEvent,
        eventStatus = eventStatus
    )

}

@Composable
fun EventRowTemplate(
    modifier: Modifier,
    appEvent: AppEvent,
    eventStatus: EventStatus
) {
    Row(
        modifier = modifier
            .border(
                shape = RoundedCornerShape(8.dp),
                width = 1.dp,
                color = Color.Gray
            )
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        shape = CircleShape,
                        color = appEvent.appEventType.toColor()
                    )
            )
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = appEvent.appEventType.name,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    text = appEvent.timestamp.toTimeStampPretty(),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Thin
                    )
                )
            }

        }

        Text(
            text = eventStatus.status,
            color = eventStatus.color,
            style = TextStyle(
                fontSize = 14.sp
            )
        )

    }
}

data class EventStatus(
    val status: String,
    val color: Color
)