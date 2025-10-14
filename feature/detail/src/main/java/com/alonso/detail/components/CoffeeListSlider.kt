package com.alonso.detail.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import kotlin.math.absoluteValue


@Composable
fun CoffeeListSlider(
    modifier: Modifier = Modifier,
    imageList: List<Int>
) {
    if (imageList.isEmpty()) return
    val pagerState = rememberPagerState(pageCount = { imageList.size })

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxWidth(),
        pageSpacing = 5.dp,
        contentPadding = PaddingValues(horizontal = 90.dp)
    ) { pageIndex ->
        CoffeeImageCard(
            imageResId = imageList[pageIndex],
            pageIndex = pageIndex,
            pagerState = pagerState
        )
    }
}


@Composable
private fun CoffeeImageCard(
    modifier: Modifier = Modifier,
    imageResId: Int,
    pageIndex: Int,
    pagerState: PagerState
) {
    val blurRadius = remember(pagerState.currentPage, pageIndex) {
        if (pagerState.currentPage == pageIndex) 0.dp else 12.dp
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height((70 + 420f).dp)
            .pagerTransition(pageIndex, pagerState)
            .blur(radius = blurRadius),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        CoffeeImage(imageResId = imageResId)
        CoffeeShadow(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-110).dp)
        )
    }
}

@Composable
private fun CoffeeImage(
    imageResId: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = "Coffee image",
        modifier = modifier
            .height(320.dp)
            .width(190.dp)
            .aspectRatio(190.dp / 320.dp),
        contentScale = ContentScale.Fit
    )
}

@Composable
private fun CoffeeShadow(modifier: Modifier = Modifier) {
    val shadowBrush = remember {
        Brush.radialGradient(
            colors = listOf(
                Color(0x36808080), // Center - more opaque
                Color(0x29606060), // Mid-inner
                Color(0x1C404040), // Mid-outer
                Color(0x0F202020), // Outer
                Color.Transparent   // Edge
            ),
            radius = 10.dp.value
        )
    }

    Canvas(modifier = modifier) {
        scale(
            scaleX = 14f,
            scaleY = 2f
        ) {
            drawCircle(
                brush = shadowBrush,
                radius = 5.dp.toPx()
            )
        }
    }
}

private fun Modifier.pagerTransition(pageIndex: Int, pagerState: PagerState) =
    graphicsLayer {
        val pageOffset = (pagerState.currentPage - pageIndex + pagerState.currentPageOffsetFraction)
            .absoluteValue

        val offsetFraction = 1f - pageOffset.coerceIn(0f, 1f)
        val scaleValue = lerp(
            start = 70.dp,
            stop = 110.dp,
            fraction = offsetFraction
        )
        scaleY = scaleValue / 100.dp
        translationY = lerp(
            start = 0f,
            stop = 380f,
            fraction = offsetFraction
        )
    }
