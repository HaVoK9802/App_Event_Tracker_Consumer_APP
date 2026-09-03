package com.example.analytics.presentation.Statistics

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.analytics.domain.mappers.toColor
import com.example.app_event_tracker.domain.models.AppEventType


@Composable
fun EventStatCardTypeOne(
    modifier: Modifier,
    statName: String,
    eventCount: Int
){
    Row(
        modifier = modifier.height(88.dp)
            .padding(4.dp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(8.dp),
                color = Color.Gray)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,

        ){
        Column(
            modifier = Modifier.wrapContentSize().padding(start = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ){
            Text(
                text = statName,
                style = TextStyle(
                    fontWeight = FontWeight.Light
                )
            )
            Text(
                text = eventCount.toString(),
                style = TextStyle(
                    fontSize = 32.sp,
                    color = Color(0xFF6200FF)
                )
            )
        }
    }
}