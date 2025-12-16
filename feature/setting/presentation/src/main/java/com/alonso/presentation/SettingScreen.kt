package com.alonso.presentation

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alonso.designsystem.CoffeeGoTheme
import com.alonso.designsystem.R
import com.alonso.ui_components.base.AppSharedViewModel
import com.alonso.ui_components.base.ThemeViewModel

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    themeViewModel: ThemeViewModel = AppSharedViewModel.themeViewModel
) {
    val themeState = themeViewModel.themeState.collectAsStateWithLifecycle()
    Scaffold(containerColor = CoffeeGoTheme.colors.backgroundHome) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .background(
                            color = CoffeeGoTheme.colors.coffeeCardBackgroundSecondary,
                            shape = RoundedCornerShape(100.dp)
                        )
                        .padding(16.dp),
                    contentAlignment = Alignment.Center

                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_coffee_bean),
                        contentDescription = "App Logo",
                        modifier = Modifier
                            .size(60.dp),
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text("Go Coffee \nv0.0.9", style = TextStyle(textAlign = TextAlign.Center))
            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_favorites),
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Terms and Conditions")
                }

                Switch(
                    checked = themeState.value,
                    onCheckedChange = { isDark ->
                        themeViewModel.toggleTheme(isDark)
                    }
                )
            }


        }
    }

}

