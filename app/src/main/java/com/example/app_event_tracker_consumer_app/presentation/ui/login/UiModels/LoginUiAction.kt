package com.example.app_event_tracker_consumer_app.presentation.ui.login.UiModels

sealed interface LoginUiAction {
    data object OnLoginClick: LoginUiAction
}