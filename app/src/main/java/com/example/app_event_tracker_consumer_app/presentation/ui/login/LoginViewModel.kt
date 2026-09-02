package com.example.app_event_tracker_consumer_app.presentation.ui.login

import androidx.lifecycle.ViewModel
import com.example.app_event_tracker_consumer_app.presentation.ui.login.UiModels.LoginUiAction
import com.example.app_event_tracker_consumer_app.presentation.ui.login.UiModels.LoginUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    private val _uiEvent = Channel<LoginUiEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onUiAction(loginUiAction: LoginUiAction){
        when(loginUiAction){
            LoginUiAction.OnLoginClick -> {
              _uiEvent.trySend(LoginUiEvent.NavigateToPurchaseScreens)
            }
        }
    }
}