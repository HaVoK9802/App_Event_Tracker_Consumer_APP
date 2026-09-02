package com.example.app_event_tracker_consumer_app.presentation.ui.Purchase

import androidx.lifecycle.ViewModel
import com.example.app_event_tracker_consumer_app.presentation.ui.login.UiModels.LoginUiAction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(): ViewModel() {



    fun onUiAction(loginUiAction: LoginUiAction){
        when(loginUiAction){
            LoginUiAction.OnLoginClick -> {

            }
        }
    }
}