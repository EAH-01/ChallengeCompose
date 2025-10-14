package com.alonso.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CofferDetailsCard(
    drink: DrinkItem,
    onOrderClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(6.dp))
        PriceAndVolumeRow(
            price = drink.price,
            volume = drink.volume
        )
        DrinkTitle(title = drink.name)
        DrinkDescription(description = drink.description)
        OrderButton(onClick = onOrderClick)
    }
}


data class DrinkItem(
    val id: String = "",
    val name: String,
    val price: String,
    val volume: String,
    val description: String,
    val imageUrl: String? = null
)