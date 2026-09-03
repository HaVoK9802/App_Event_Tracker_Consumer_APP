package com.example.analytics.presentation.Statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.RestartAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.analytics.domain.Statistics.models.CollectiveEventStatistics
import com.example.app_event_tracker.domain.models.AppEventType

@Composable
fun StatisticsScreen(
    modifier: Modifier,
    collectiveEventStatistics: CollectiveEventStatistics,
    onRetryClicked: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Statistics",
                textAlign = TextAlign.Start
            )
            IconButton(
                onClick = {
                    onRetryClicked.invoke()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.RestartAlt,
                    contentDescription = "Retry",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {

            Column() {
                Row {
                    EventStatCardTypeOne(
                        modifier = Modifier.weight(1f),
                        statName = "Total Events Processed",
                        eventCount = collectiveEventStatistics.totalProcessedEvents?.count ?: 0
                    )
                    EventStatCardTypeOne(
                        modifier = Modifier.weight(1f),
                        statName = """
                            Total Visits
                            (Unique Session)
                        """.trimIndent(),
                        eventCount = collectiveEventStatistics.visits?.count ?: 0
                    )
                }
                Row {
                    EventStatCardTypeTwo(
                        modifier = Modifier.weight(1f),
                        eventName = AppEventType.INSTALL,
                        eventCount = collectiveEventStatistics.installs?.count ?: 0
                    )
                    EventStatCardTypeTwo(
                        modifier = Modifier.weight(1f),
                        eventName = AppEventType.SCREEN_VISIT,
                        eventCount = collectiveEventStatistics.screenVisits?.count ?: 0
                    )
                }
                Row {
                    EventStatCardTypeTwo(
                        modifier = Modifier.weight(1f),
                        eventName = AppEventType.ADD_TO_CART,
                        eventCount = collectiveEventStatistics.itemsAddedToCart?.count ?: 0
                    )
                    EventStatCardTypeTwo(
                        modifier = Modifier.weight(1f),
                        eventName = AppEventType.PURCHASE,
                        eventCount = collectiveEventStatistics.purchases?.count ?: 0
                    )
                }
            }

            StatTable(
                modifier = Modifier.fillMaxWidth(),
                collectiveEventStatistics = collectiveEventStatistics
            )
        }
    }
}

