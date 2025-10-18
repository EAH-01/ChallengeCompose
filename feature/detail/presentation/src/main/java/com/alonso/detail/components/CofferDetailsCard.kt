package com.alonso.detail.components

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.AppTheme
import com.alonso.designsystem.R
import com.alonso.navigation.CoffeeItem

@Composable
fun CofferDetailsCard(
    drink: CoffeeItem?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(
                color = Color(0xFFF5F1EE),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            TitleDetailCard(
                name = drink?.name.orEmpty(),
                volume = "354",
                price = drink?.price ?: 0.0,
                category = "Coffee",
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(
                text = drink?.description.orEmpty(),
                style = AppTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Start,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()

            )

        }
        CoffeeRating(
            modifier = Modifier.align(Alignment.BottomEnd),
            value = drink?.qualification.toString()
        )
    }

}

@Composable
fun CoffeeRating(modifier: Modifier = Modifier, value: String) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterEnd
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.ic_star),
                tint = Color(0xFFD9A012),
                contentDescription = null
            )
            Text(
                value, style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true, name = "Full Screen")
@Composable
private fun CofferDetailsCardPreview() {
    CofferDetailsCard(
        CoffeeItem(
            id = 1,
            name = "Espresso",
            price = 2.50,
            description = "Un shot concentrado de café con un sabor intenso y una crema rica.",
            image = "https://static.vecteezy.com/system/resources/previews/048/095/748/non_2x/shot-of-rich-espresso-with-a-creamy-top-png.png",
            qualification = 5
        )
    )


}
