package com.example.analytics.domain.mappers

import androidx.compose.ui.graphics.Color
import com.example.app_event_tracker.domain.models.AppEventType


fun AppEventType.toColor(): Color {
    return when (this) {
        is AppEventType.StrictlyOnceEvent.Install -> {
            Color(0xFF4A8D5A)
        }

        is AppEventType.OncePerSessionEvent.Visit -> {
            Color(0xFF799AF6)

        }

        is AppEventType.MultipleEvent.AddToCart -> {
            Color(0xFF63558C)

        }

        is AppEventType.MultipleEvent.Purchase -> {
            Color(0xFFE0CB7C)
        }

        is AppEventType.Unknown -> Color.Red

    }
}