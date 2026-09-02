package com.example.app_event_tracker_consumer_app.presentation.ui.login.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import com.example.app_event_tracker_consumer_app.presentation.ui.login.UiModels.LoginUiAction.OnLoginClick


@Composable
fun LoginScreen(
    modifier: Modifier,
    onLoginClick: () -> Unit
){
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) {  innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            Button(
                modifier = Modifier.align(Alignment.Center),
                onClick = {onLoginClick.invoke()}
            ) {
                Text("Login")
            }
        }
    }
}