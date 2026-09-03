package com.example.analytics.presentation.Statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
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

@Composable
fun StatisticsScreen(
    modifier: Modifier,
    collectiveEventStatistics: CollectiveEventStatistics,
    onRetryClicked: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ){
        Row(
            modifier = Modifier.height(48.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
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
                    tint =  MaterialTheme.colorScheme.primary
                )
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {


            item {
                StatTable(
                    modifier = Modifier.fillMaxWidth(),
                    collectiveEventStatistics = collectiveEventStatistics
                )
            }
        }
    }
}

