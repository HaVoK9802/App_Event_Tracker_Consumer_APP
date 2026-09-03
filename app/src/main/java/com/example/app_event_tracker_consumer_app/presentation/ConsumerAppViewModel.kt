package com.example.app_event_tracker_consumer_app.presentation

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.app_event_tracker.domain.models.AppEventType
import com.example.app_event_tracker_consumer_app.ConsumerApplication
import com.example.app_event_tracker_consumer_app.presentation.models.CartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ConsumerAppViewModel @Inject constructor(
    private val application: Application
) : ViewModel() {


    var sharedCart = mutableStateListOf<CartItem>()
        private set


    fun addItemToCart(item: CartItem) {
        if (!sharedCart.contains(item)) {
            sharedCart.add(item)
        }
        (application as ConsumerApplication).appEventTracker.trackEvent(
            """
                    {
                      "event_type": "${AppEventType.ADD_TO_CART}",
                      "item_id": "${item.id}"
                    }
                    """.trimIndent()
        )
    }

    fun purchaseItems(item: CartItem){
        if (sharedCart.isNotEmpty()) {
            sharedCart.remove(item)
        }
        (application as ConsumerApplication).appEventTracker.trackEvent(
            """
                    {
                      "event_type": "${AppEventType.PURCHASE}",
                      "item_id": "${item.id}"
                    }
                    """.trimIndent()
        )
    }

    fun visitEvent() {
        (application as ConsumerApplication).appEventTracker.trackEvent(
            """
                    {
                      "event_type": "${AppEventType.VISIT}"
                    }
                    """.trimIndent()
        )
    }
    fun screenVisitEvent(screenName: String) {
        (application as ConsumerApplication).appEventTracker.trackEvent(
            """
                    {
                      "event_type": "${AppEventType.SCREEN_VISIT}",
                      "screen_name": "$screenName"
                    }
                    """.trimIndent()
        )
    }

    fun logout() {
        (application as ConsumerApplication).appEventTracker.destroyTracker()
    }


}