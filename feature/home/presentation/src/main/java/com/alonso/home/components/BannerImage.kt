package com.alonso.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BannerImage(modifier: Modifier = Modifier, show: Boolean = true) {
    AnimatedVisibility(
        visible = show,
        enter = fadeIn(animationSpec = tween()),
        exit = fadeOut(animationSpec = tween())
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(
                    color = Color(0xFFFEF2E6),
                    shape = RoundedCornerShape(12.dp)
                )
        )
    }

}