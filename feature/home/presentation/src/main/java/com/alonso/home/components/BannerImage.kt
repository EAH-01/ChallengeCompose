package com.alonso.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.alonso.designsystem.R

@Composable
fun BannerImage(modifier: Modifier = Modifier, show: Boolean = true) {
    AnimatedVisibility(
        visible = show,
        enter = fadeIn(animationSpec = tween()),
        exit = fadeOut(animationSpec = tween())
    ) {
        Image(
            painter = painterResource(id = R.drawable.banner_images),
            modifier = modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Fit,
            contentDescription = ""
        )

    }

}