package com.alonso.challengecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import com.alonso.challengecompose.ui.theme.ChallengeComposeTheme
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChallengeComposeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Blue
                ) { innerPadding ->
                    CoffeeStarBuckScree()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoffeeStarBuckScree(
    modifier: Modifier = Modifier
) {
    //val maxTranslationY = 450f // Increased for more downward movement
    val maxTranslationY = 420f // Increased for more downward movement
    val imageList = listOf(
        R.drawable.coffee_starbuck,
        R.drawable.coffee_starbuck,
        R.drawable.coffee_starbuck,
        R.drawable.coffee_starbuck,
        R.drawable.coffee_starbuck,
        R.drawable.coffee_starbuck,
        R.drawable.coffee_starbuck,
        R.drawable.coffee_starbuck,
        R.drawable.coffee_starbuck,
        R.drawable.coffee_starbuck,
        R.drawable.coffee_starbuck,
    )
    val pagerState = rememberPagerState { imageList.size }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth(),
        pageSpacing = 5.dp,
        contentPadding = PaddingValues(horizontal = 90.dp)
    ) { page ->
        Box(
            modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth()
                .height((70 + maxTranslationY).dp) // Fixed: Use image height + translation
                .graphicsLayer {
                    val pageOffset =
                        (pagerState.currentPage - page + pagerState.currentPageOffsetFraction).absoluteValue
                    lerp(
                        start = 70.dp,
                        stop = 120.dp,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleY = scale / 100.dp
                    }
                    translationY = lerp(
                        start = 0f,
                        stop = 400f, // Use the constant here
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .blur(
                    radius = if (pagerState.currentPage == page) 0.dp else 12.dp // Blur non-current pages
                ),
            contentAlignment = Alignment.TopCenter // Changed from Center to TopCenter
        ) {
            Image(
                painter = painterResource(id = imageList[page]),
                contentDescription = "",
                modifier = Modifier
                    .height(320.dp)
                    .width(190.dp),
                contentScale = ContentScale.Crop
            )
        }

    }
}
