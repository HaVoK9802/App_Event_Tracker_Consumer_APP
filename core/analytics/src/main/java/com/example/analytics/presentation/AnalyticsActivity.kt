package com.example.analytics.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.analytics.presentation.Queue.QueuedEventsScreen
import com.example.analytics.presentation.Queue.QueueScreenViewModel
import com.example.analytics.presentation.Statistics.StatisticsScreen
import com.example.analytics.presentation.Statistics.StatisticsScreenViewModel
import com.example.analytics.presentation.navigation.AnalyticsRoutes
import com.example.analytics.presentation.navigation.navigateAndClearBackStack
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
                val isQueueScreen = navBackStackEntry?.destination?.hasRoute<AnalyticsRoutes.QueuedEvents>() == true
                val isStatisticsScreen = navBackStackEntry?.destination?.hasRoute<AnalyticsRoutes.Statistics>() == true
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                    topBar = {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(top = 24.dp).padding(bottom = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = "Ad Attribution Tracker",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Black
                                )
                            )
                        }
                    },
                    bottomBar = {
                        Column {
                            HorizontalDivider(
                                modifier = Modifier.fillMaxWidth(),
                                thickness = 1.dp
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(64.dp),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier.clickable(
                                        enabled = true,
                                        onClick = {
                                            navController.navigateAndClearBackStack(AnalyticsRoutes.QueuedEvents)
                                        }
                                    ),
                                    text = "Queue",
                                    textDecoration = if (isQueueScreen) TextDecoration.Underline else TextDecoration.None

                                )
                                Text(
                                    modifier = Modifier.clickable(
                                        enabled = true,
                                        onClick = {
                                            navController.navigateAndClearBackStack(AnalyticsRoutes.Statistics)
                                        }
                                    ),
                                    text = "Statistics",
                                    textDecoration = if (isStatisticsScreen) TextDecoration.Underline else TextDecoration.None
                                )
                            }
                        }
                    }
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
                            QueuedEventsScreen(
                                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp).padding(top = 16.dp),
                                events = eventList
                            )
                        }

                        composable<AnalyticsRoutes.Statistics>{
                            val statisticsScreenViewModel = hiltViewModel<StatisticsScreenViewModel>()
                            StatisticsScreen(
                                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp).padding(top = 16.dp),
                                collectiveEventStatistics = statisticsScreenViewModel.collectiveEventStatistics.value,
                                onRetryClicked = {
                                    statisticsScreenViewModel.fetchData()
                                }
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