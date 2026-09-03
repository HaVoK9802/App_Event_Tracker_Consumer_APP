package com.example.analytics.domain.mappers

import androidx.compose.ui.graphics.Color
import com.example.app_event_tracker.domain.models.AppEventType


fun AppEventType.toColor(isDark: Boolean = false): Color {
    return when (this) {
        is AppEventType.StrictlyOnceEvent.Install -> {
            if (isDark) {
                Color(0xFF305D39)
            } else {
                Color(0xFF539D56)
            }
        }

        is AppEventType.OncePerSessionEvent.Visit -> {

            if (isDark) {
                Color(0xFF415788)
            } else {
                Color(0xFF799AF6)
            }


        }

        is AppEventType.OncePerSessionEvent.ScreenVisit -> {
            if (isDark) {
                Color(0xFF93384D)
            } else {
                Color(0xFFF55E85)
            }
        }

        is AppEventType.MultipleEvent.AddToCart -> {


            if (isDark) {
                Color(0xFF4E436E)
            } else {
                Color(0xFF63558C)
            }

        }

        is AppEventType.MultipleEvent.Purchase -> {
            if (isDark) {
                Color(0xFF867A49)
            } else {
                Color(0xFFE0CB7C)
            }
        }

        is AppEventType.Unknown -> Color.Red

    }
}