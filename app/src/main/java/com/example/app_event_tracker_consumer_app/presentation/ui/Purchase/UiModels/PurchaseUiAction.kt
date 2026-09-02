package com.example.app_event_tracker_consumer_app.presentation.ui.Purchase.UiModels

sealed interface PurchaseUiAction {
    data class OnAddToCart(val itemId: String): PurchaseUiAction
    data class OnTabSwitch(val screenName: String): PurchaseUiAction
}