package com.alonso.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CoffeeCard(
    coffeeName: String,
    price: String,
    imageRes: Int,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .width(200.dp)
            .height(260.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFF5F1EE),
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            CoffeeImageContainer(
                imageRes = imageRes,
                imageContainerHeight = 180.dp,
                imageHeight = 150.dp,
                imageWidth = 110.dp,
                cornerRadius = 8.dp,
                imageBackgroundColor = Color(0xFFF5F1EE),
                imageBottomPadding = 12.dp
            )
            Spacer(modifier = Modifier.height(14.dp))
            CoffeeInfo(
                coffeeName = coffeeName,
                price = price,
                textMaxWidth = 110.dp
            )
        }
    }
}


@Composable
private fun CoffeeImageContainer(
    imageRes: Int,
    imageContainerHeight: Dp,
    imageHeight: Dp,
    imageWidth: Dp,
    cornerRadius: Dp,
    imageBackgroundColor: Color,
    imageBottomPadding: Dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(imageContainerHeight)
            .background(
                color = imageBackgroundColor,
                shape = RoundedCornerShape(cornerRadius)
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = "Imagen del café",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(bottom = imageBottomPadding)
                .height(imageHeight)
                .width(imageWidth)
        )
    }
}

@Composable
private fun CoffeeInfo(
    coffeeName: String,
    price: String,
    textMaxWidth: androidx.compose.ui.unit.Dp
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            modifier = Modifier.width(textMaxWidth),
            text = coffeeName,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            style = TextStyle(
                fontWeight = FontWeight.W500,
                color = Color.Black, fontSize = 12.sp
            )
        )

        Text(
            text = "$$price",
            style = TextStyle(
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

/**
 * Preview del componente CoffeeCard
 */
@Preview(showBackground = true)
@Composable
private fun CoffeeCardPreview() {
    MaterialTheme {
        CoffeeCard(
            coffeeName = "Vietnamese Coconut Coffee",
            price = "11",
            imageRes = com.alonso.designsystem.R.drawable.coffee_starbuck
        )
    }
}