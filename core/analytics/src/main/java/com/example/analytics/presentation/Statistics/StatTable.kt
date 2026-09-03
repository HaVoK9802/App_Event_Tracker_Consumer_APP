package com.example.analytics.presentation.Statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.analytics.domain.Statistics.models.CollectiveEventStatistics
import com.example.analytics.domain.Statistics.models.EventStat
import com.example.analytics.domain.mappers.toColor
import com.example.app_event_tracker.domain.models.AppEventType
import java.lang.String.format

@Composable
fun StatTable(
    modifier: Modifier,
    collectiveEventStatistics: CollectiveEventStatistics
) {
     Column(
         modifier = modifier
     ){
         Text(
             modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
             text = "Event Breakdown",
             style = TextStyle(
                 fontSize = 14.sp,
                 textAlign = TextAlign.Left,
                 fontWeight = FontWeight.Bold
             )
         )

         Column (
            modifier = Modifier.fillMaxWidth().border(
                shape = RoundedCornerShape(8.dp),
                width = 1.dp,
                color = Color.Gray
            ).padding(8.dp)
         ) {
             StatTableRow()
             StatTableRow(
                 collectiveEventStatistics.installs,
                 "Install"
             )
             StatTableRow(
                 collectiveEventStatistics.visits,
                 "Visit"
             )
             StatTableRow(
                 collectiveEventStatistics.itemsAddedToCart,
                 "AddToCart"
             )
             StatTableRow(
                 collectiveEventStatistics.purchases,
                 "Purchase"
             )
         }
     }
}


@Composable
fun StatTableRow(
    eventStat: EventStat? = null,
    appEventType: String? = null
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceAround
    ){
        if(eventStat!=null && appEventType!=null) {
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 2.dp,
                color = Color.Gray
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1.5f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(appEventType!=null && eventStat!=null) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(
                                shape = CircleShape,
                                color = AppEventType.fromString(appEventType).toColor()
                            )
                    )
                }
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = appEventType?:"Event",
                )
            }
            Text(
                modifier = Modifier.weight(1.25f),
                text = eventStat?.count?.toString() ?: "Count"
            )
            Text(
                modifier = Modifier.weight(1.25f),
                text = eventStat?.percentage?.let {
                    "%.2f".format(it) + " %"
                }?:"Percentage"
            )
        }

    }


}