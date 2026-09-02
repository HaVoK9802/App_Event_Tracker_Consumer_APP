package com.example.app_event_tracker_consumer_app.presentation.ui.login.UiModels

sealed interface LoginUiEvent {
    data object NavigateToPurchaseScreens: LoginUiEvent
}