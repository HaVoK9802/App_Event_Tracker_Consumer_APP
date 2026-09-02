package com.example.app_event_tracker_consumer_app.presentation.ui.Purchase.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun Screen1(
    onItemClick: (String)-> Unit
){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {

        Item(
            "Item A",
            onItemClick = onItemClick
        )
        Item(
            "Item B",
            onItemClick = onItemClick
        )
        Item(
            "Item C",
            onItemClick = onItemClick
        )
        Item(
            "Item D",
            onItemClick = onItemClick
        )
    }
}

@Composable
fun Screen2(
    onItemClick: (String)-> Unit
){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {

        Item(
            "Item E",
            onItemClick = onItemClick
        )
        Item(
            "Item F",
            onItemClick = onItemClick
        )
        Item(
            "Item G",
            onItemClick = onItemClick
        )
        Item(
            "Item H",
            onItemClick = onItemClick
        )
    }
}