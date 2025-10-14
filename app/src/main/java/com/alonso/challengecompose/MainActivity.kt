package com.alonso.challengecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.alonso.challengecompose.ui.theme.ChallengeComposeTheme
import com.alonso.detail.DetailScreen
import com.alonso.home.HomeScreen
import com.alonso.navigation.AppNavigator
import com.alonso.navigation.AppScreen
import com.alonso.navigation.App.LocalComposeNavigator
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    internal lateinit var composeNavigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            ChallengeComposeTheme {
                NavigationApp(appNavigator = composeNavigator)
            }
        }
    }
}

@Composable
fun NavigationApp(
    modifier: Modifier = Modifier,
    appNavigator: AppNavigator
) {
    val backStack: NavBackStack<NavKey> = rememberNavBackStack(AppScreen.Home)
    LaunchedEffect(Unit) { appNavigator.initialize(backStack) }
    CompositionLocalProvider(LocalComposeNavigator provides appNavigator) {
        NavDisplay(
            modifier = modifier,
            backStack = backStack,
            onBack = { appNavigator.popBack() },
            entryProvider = entryProvider {
                entry<AppScreen.Home> { HomeScreen() }
                entry<AppScreen.Detail> { entry -> DetailScreen() }
            }
        )
    }

}

@Preview(showBackground = true, name = "Full Screen")
@Composable
private fun HomeScreenPreview() {
    ChallengeComposeTheme {
        DetailScreen()
    }
}
