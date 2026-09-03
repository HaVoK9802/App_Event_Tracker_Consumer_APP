package com.example.app_event_tracker_consumer_app.presentation.ui.Cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.app_event_tracker_consumer_app.presentation.models.CartItem

@Composable
fun ItemsInCartScreen(
    cartItems: List<CartItem>,
    onPurchaseCartItem: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            cartItems.forEach { it ->
                ItemInCart(
                    name = it.id,
                    onPurchaseClicked = {
                        onPurchaseCartItem.invoke(it)
                    }
                )
            }
        }
    }
}