package com.alonso.challengecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.alonso.challengecompose.ui.theme.AppTheme
import com.alonso.detail.DetailScreen
import com.alonso.home.HomeScreen
import com.alonso.navigation.AppNavigator
import com.alonso.navigation.AppScreen
import com.alonso.splash.SplashScreen
import com.alonso.ui_components.base.Loader
import com.alonso.ui_components.components.LoaderModal
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
        enableEdgeToEdge()
        setContent {
            AppTheme(appNavigator = composeNavigator) {
                LoaderModal(loader.loading.collectAsState().value)
                val backStack = rememberNavBackStack(AppScreen.Splash)
                LaunchedEffect(Unit) { composeNavigator.initialize(backStack) }
                NavDisplay(
                    backStack = backStack,
                    onBack = { composeNavigator.popBack() },
                    entryProvider = entryProvider {
                        entry<AppScreen.Splash> { SplashScreen() }
                        entry<AppScreen.Home> { HomeScreen() }
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
