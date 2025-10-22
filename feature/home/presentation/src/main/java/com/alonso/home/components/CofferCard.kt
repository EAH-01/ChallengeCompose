package com.alonso.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.AppTheme
import com.alonso.ui_components.components.LoadImage

@Composable
fun CoffeeCard(
    modifierParent: Modifier = Modifier,
    modifierImg: Modifier = Modifier,
    coffeeName: String,
    price: String,
    imageUrl: String,
    onClick: () -> Unit = {}
) {

    Box(
        modifier = modifierParent
            .clickable(onClick = onClick)
            .width(200.dp)
            .height(215.dp)
            .border(
                width = 1.dp,
                color = AppTheme.colors.coffeeCardBorder,
                shape = RoundedCornerShape(18.dp)
            )
            .background(
                color = AppTheme.colors.coffeeCardBackgroundPrimary,
                shape = RoundedCornerShape(18.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            CoffeeImageContainer(
                modifier = modifierImg,
                imageUrl = imageUrl,
                imageContainerHeight = 150.dp,
                imageHeight = 130.dp,
                imageWidth = 100.dp,
                cornerRadius = 8.dp,
                imageBackgroundColor = AppTheme.colors.coffeeCardBackgroundSecondary,
                imageBottomPadding = 6.dp
            )
            Spacer(modifier = Modifier.height(14.dp))
            CoffeeInfo(
                coffeeName = coffeeName,
                price = price
            )
        }
    }
}


@Composable
private fun CoffeeImageContainer(
    modifier: Modifier = Modifier,
    imageUrl: String,
    imageContainerHeight: Dp,
    imageHeight: Dp,
    imageWidth: Dp,
    cornerRadius: Dp,
    imageBackgroundColor: Color,
    imageBottomPadding: Dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(imageContainerHeight)
            .background(
                color = imageBackgroundColor,
                shape = RoundedCornerShape(cornerRadius)
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        LoadImage(
            url = imageUrl,
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
    price: String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            modifier = Modifier
                .weight(0.7f)
                .fillMaxHeight(),
            text = coffeeName,
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            style = TextStyle(
                fontWeight = FontWeight.W500,
                color = AppTheme.colors.textColor,
                fontSize = 12.sp
            )
        )

        Text(
            modifier = Modifier.weight(0.3f),
            text = "$$price",
            style = TextStyle(
                color = AppTheme.colors.textColor,
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
            imageUrl = "https://png.pngtree.com/png-clipart/20240220/original/pngtree-latte-macchiato-coffee-glass-png-image_14366823.png"
        )
    }
}