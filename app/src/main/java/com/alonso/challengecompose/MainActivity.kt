package com.alonso.challengecompose

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.alonso.challengecompose.ui.theme.AppTheme
import com.alonso.challengecompose.utils.connectivityState
import com.alonso.designsystem.AppTheme
import com.alonso.detail.DetailScreen
import com.alonso.home.HomeScreen
import com.alonso.navigation.AppNavigator
import com.alonso.navigation.AppScreen
import com.alonso.splash.SplashScreen
import com.alonso.ui_components.base.Loader
import com.alonso.ui_components.components.LoaderModal
import com.alonso.ui_components.components.NetworkMessageBar
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    internal lateinit var composeNavigator: AppNavigator

    @Inject
    internal lateinit var loader: Loader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.auto(Color.RED, Color.BLUE))
        setContent {
            AppTheme(appNavigator = composeNavigator) {
                val connection by connectivityState()
                LoaderModal(loader.loading.collectAsState().value)
                val backStack = rememberNavBackStack(AppScreen.Splash)
                LaunchedEffect(Unit) { composeNavigator.initialize(backStack) }
                Scaffold(
                    containerColor = AppTheme.colors.backgroundHome,
                    topBar = {
                        NetworkMessageBar(
                            isConnected = connection,
                            modifier = Modifier.statusBarsPadding()
                        )
                    }
                ) { innerPadding ->
                    NavDisplay(
                        backStack = backStack,
                        onBack = { composeNavigator.popBack() },
                        entryProvider = entryProvider {
                            entry<AppScreen.Home> {
                                HomeScreen(modifier = Modifier.padding(top = innerPadding.calculateTopPadding()))
                            }
                            entry<AppScreen.Splash> {
                                SplashScreen()
                            }
                            entry<AppScreen.Detail> {
                                DetailScreen(
                                    listCoffee = it.listCoffee,
                                    coffeeClicked = it.coffeeClicked
                                )
                            }
                        },
                        transitionSpec = {
                            ContentTransform(
                                slideInHorizontally(initialOffsetX = { it }),
                                slideOutHorizontally(targetOffsetX = { -it })
                            )
                        },
                        popTransitionSpec = {
                            ContentTransform(
                                slideInHorizontally(initialOffsetX = { -it }),
                                slideOutHorizontally(targetOffsetX = { it })
                            )
                        }

                    )
                }

            }
        }
    }
}
