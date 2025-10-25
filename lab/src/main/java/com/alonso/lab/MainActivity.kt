package com.alonso.lab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.R
import com.alonso.lab.ui.theme.ChallengeComposeTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChallengeComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SplashScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    var animationStarted by remember { mutableStateOf(false) }

    // Unified alpha animation that controls both fade-in and scale timing
    val alpha by animateFloatAsState(
        targetValue = if (animationStarted) 1f else 0f,
        animationSpec = tween(durationMillis = 1300),
        label = "alphaAnimation"
    )

    // Scale animation that starts after alpha reaches 1f
    val scale by animateFloatAsState(
        targetValue = if (animationStarted && alpha >= 0.9f) 1f else 2.5f,
        animationSpec = tween(durationMillis = 1000),
        label = "scaleAnimation"
    )

    // Start animation on composition
    LaunchedEffect(Unit) {
        delay(200) // Small delay for better visual effect
        animationStarted = true
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF4F5F0)),
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
                    "Challenge Compose", color = Color(0xFF5A270D),
                    style = TextStyle(fontSize = 18.sp)
                )
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    ChallengeComposeTheme {
        SplashScreen()
    }
}