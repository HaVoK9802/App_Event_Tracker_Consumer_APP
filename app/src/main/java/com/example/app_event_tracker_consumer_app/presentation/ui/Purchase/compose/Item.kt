package com.example.app_event_tracker_consumer_app.presentation.ui.Purchase.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Item(
    name: String,
    onItemClick: (String) -> Unit
){
    Button(
        modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
        onClick = {
            onItemClick.invoke(name)
        }
    ){
        Text("$name - Add to Cart")
    }
}