package com.alonso.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.AppTheme
import com.alonso.designsystem.R
import com.alonso.navigation.AppNavigator
import com.alonso.navigation.AppScreen
import com.alonso.navigation.navRoot
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    appNavigator: AppNavigator = navRoot
) {
    var animationStarted by remember { mutableStateOf(false) }

    // Unified alpha animation that controls both fade-in and scale timing
    val alpha by animateFloatAsState(
        targetValue = if (animationStarted) 1f else 0f,
        animationSpec = tween(durationMillis = 1300),
        label = "alphaAnimation"
    )

    // Scale animation that starts after alpha reaches 1f
    val scale by animateFloatAsState(
        targetValue = if (animationStarted && alpha >= 0.9f) 1f else 2.3f,
        animationSpec = tween(durationMillis = 1000),
        label = "scaleAnimation"
    )

    // Start animation on composition
    LaunchedEffect(Unit) {
        delay(200) // Small delay for better visual effect
        animationStarted = true
        delay(2000)
        appNavigator.goTo(AppScreen.Home)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.backgroundSplash),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_coffee_bean),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(90.dp)
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        alpha = alpha
                    ),
            )
            Spacer(modifier = Modifier.height(12.dp))
            AnimatedVisibility(visible = scale < 2f) {
                Text(
                    "CoffeeGo", color = AppTheme.colors.textIconSplash,
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.roboto_flex))
                    )
                )
            }

        }

    }
}