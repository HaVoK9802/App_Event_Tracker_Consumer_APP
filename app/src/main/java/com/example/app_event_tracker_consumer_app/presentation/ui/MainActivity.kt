package com.example.app_event_tracker_consumer_app.presentation.ui

import android.content.Intent
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
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import com.example.analytics.presentation.AnalyticsActivity
import com.example.app_event_tracker_consumer_app.presentation.models.CartItem
import com.example.app_event_tracker_consumer_app.presentation.ConsumerAppViewModel
import com.example.app_event_tracker_consumer_app.presentation.navigation.AppRoutes
import com.example.app_event_tracker_consumer_app.presentation.navigation.PurchaseRoute
import com.example.app_event_tracker_consumer_app.presentation.navigation.navigateAndClearBackStack
import com.example.app_event_tracker_consumer_app.presentation.navigation.navigateToPurchaseRoutes
import com.example.app_event_tracker_consumer_app.presentation.navigation.navigateToRoute
import com.example.app_event_tracker_consumer_app.presentation.theme.App_Event_Tracker_Consumer_APPTheme
import com.example.app_event_tracker_consumer_app.presentation.ui.Cart.ItemsInCartScreen
import com.example.app_event_tracker_consumer_app.presentation.ui.Purchase.compose.Screen1
import com.example.app_event_tracker_consumer_app.presentation.ui.Purchase.compose.Screen2
import com.example.app_event_tracker_consumer_app.presentation.ui.login.LoginViewModel
import com.example.app_event_tracker_consumer_app.presentation.ui.login.UiModels.LoginUiAction
import com.example.app_event_tracker_consumer_app.presentation.ui.login.UiModels.LoginUiEvent
import com.example.app_event_tracker_consumer_app.presentation.ui.login.compose.LoginScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App_Event_Tracker_Consumer_APPTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val isLoginScreen =
                    navBackStackEntry?.destination?.hasRoute<AppRoutes.LoginScreen>() == true
                val isItemCartScreen =
                    navBackStackEntry?.destination?.hasRoute<AppRoutes.ItemCartScreen>() == true
                val appViewModel: ConsumerAppViewModel = hiltViewModel()
                val isScreen1 =
                    navBackStackEntry?.destination?.hasRoute<PurchaseRoute.Screen1>() == true

                val isScreen2 =
                    navBackStackEntry?.destination?.hasRoute<PurchaseRoute.Screen2>() == true

                val isPurchaseGraph = isScreen1 || isScreen2

                val selectedTabIndex = when {
                    isScreen2 -> 1
                    else -> 0
                }
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .navigationBarsPadding(),
                    bottomBar = {
                        if (!isLoginScreen) {
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
                                            navController.navigateAndClearBackStack(AppRoutes.PurchaseGraph)
                                        }
                                    ),
                                    text = "Purchase Screens",
                                    textDecoration = if (isPurchaseGraph) TextDecoration.Underline else TextDecoration.None

                                )
                                Text(
                                    modifier = Modifier.clickable(
                                        enabled = true,
                                        onClick = {
                                            navController.navigateAndClearBackStack(AppRoutes.ItemCartScreen)
                                        }
                                    ),
                                    text = "Item Cart",
                                    textDecoration = if (isItemCartScreen) TextDecoration.Underline else TextDecoration.None
                                )
                            }
                        }

                    },
                    topBar = {
                        if (!isLoginScreen) {
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(64.dp)
                                        .padding(horizontal = 16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = if(isPurchaseGraph) "Purchase Products" else "Your Cart",
                                        fontSize = 24.sp
                                    )
                                    Button(
                                        onClick = {
                                            appViewModel.logout()
                                            finish()
                                        }
                                    ) {
                                        Text("Logout")
                                    }
                                }
                                HorizontalDivider(
                                    modifier = Modifier.fillMaxWidth(),
                                    thickness = 1.dp
                                )

                                if (isPurchaseGraph) {
                                    SecondaryTabRow(
                                        selectedTabIndex = selectedTabIndex
                                    ) {
                                        Tab(
                                            selected = selectedTabIndex == 0,
                                            onClick = {
                                                if (selectedTabIndex != 0) {
                                                    navController.navigateToPurchaseRoutes(
                                                        PurchaseRoute.Screen1
                                                    )
                                                }
                                            },
                                            text = {
                                                Text("Screen 1")
                                            }
                                        )

                                        Tab(
                                            selected = selectedTabIndex == 1,
                                            onClick = {
                                                if (selectedTabIndex != 1) {
                                                    navController.navigateToPurchaseRoutes(
                                                        PurchaseRoute.Screen2
                                                    )
                                                }
                                            },
                                            text = {
                                                Text("Screen 2")
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    },
                    floatingActionButton = {
                        if (!isLoginScreen) {
                            Button(
                                onClick = {
                                    val intent = Intent(this, AnalyticsActivity::class.java)
                                    startActivity(intent)
                                }
                            ) {
                                Text("View Analytics")
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = AppRoutes.LoginScreen
                    ) {
                        composable<AppRoutes.LoginScreen> {
                            val loginVM: LoginViewModel = hiltViewModel()
                            LaunchedEffect(Unit) {
                                loginVM.uiEvent.collect {
                                    when (it) {
                                        LoginUiEvent.NavigateToPurchaseScreens -> {
                                            navController.navigateToRoute(AppRoutes.PurchaseGraph)
                                        }
                                    }
                                }
                            }
                            LoginScreen(
                                modifier = Modifier.fillMaxSize(),
                                onLoginClick = { loginVM.onUiAction(LoginUiAction.OnLoginClick) }
                            )
                        }



                        composable<AppRoutes.ItemCartScreen> {
                            appViewModel.navigateEvent("Item Cart")
                            ItemsInCartScreen(
                                appViewModel.sharedCart,
                                onPurchaseCartItem = {
                                    appViewModel.purchaseItems(CartItem(it))
                                }
                            )
                        }

                        navigation<AppRoutes.PurchaseGraph>(
                            startDestination = PurchaseRoute.Screen1
                        ) {


                            composable<PurchaseRoute.Screen1> {
                                appViewModel.navigateEvent("Purchase Screen 2")
                                Screen1(
                                    onItemClick = {
                                        appViewModel.addItemToCart(
                                            CartItem(
                                                id = it
                                            )
                                        )
                                    }
                                )
                            }

                            composable<PurchaseRoute.Screen2> {
                                appViewModel.navigateEvent("Purchase Screen 1")
                                Screen2(
                                    onItemClick = {
                                        appViewModel.addItemToCart(
                                            CartItem(
                                                id = it
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}