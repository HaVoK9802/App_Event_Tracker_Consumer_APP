package com.example.analytics.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.analytics.presentation.Queue.ProcessingAndProcessedEventsScreen
import com.example.analytics.presentation.Queue.QueueScreenViewModel
import com.example.analytics.presentation.navigation.AnalyticsRoutes
import com.example.analytics.presentation.theme.App_Event_Tracker_Consumer_APPTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.collections.emptyList

@AndroidEntryPoint
class AnalyticsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App_Event_Tracker_Consumer_APPTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
                ) { innerPadding ->

                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = AnalyticsRoutes.QueuedEvents
                    ) {
                        composable<AnalyticsRoutes.QueuedEvents>{
                            val queueScreenViewModel = hiltViewModel<QueueScreenViewModel>()
                            val eventList by queueScreenViewModel.eventsList.collectAsStateWithLifecycle(
                                initialValue = emptyList()
                            )
                            ProcessingAndProcessedEventsScreen(
                                modifier = Modifier.fillMaxSize().padding(16.dp),
                                events = eventList
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    App_Event_Tracker_Consumer_APPTheme {
        Greeting("Android")
    }
}