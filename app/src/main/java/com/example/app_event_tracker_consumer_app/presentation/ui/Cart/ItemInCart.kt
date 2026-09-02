package com.example.app_event_tracker_consumer_app.presentation.ui.Cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ItemInCart(
    name: String,
    onPurchaseClicked: (String)-> Unit
){
    Row(
        Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(name)
        Button(
            onClick = {
                onPurchaseClicked.invoke(name)
            }
        ) {
            Text("Purchase")
        }
    }
}