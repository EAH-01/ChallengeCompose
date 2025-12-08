package com.alonso.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.AppTheme
import com.alonso.home.HomeScreen
import com.alonso.search.SearchScreen

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    var currentDestination by rememberSaveable { mutableStateOf(BottomNavItem.OverviewScreen.path) }

    val navItems = remember {
        listOf(
            BottomNavItem.OverviewScreen,
            BottomNavItem.SearchScreen,
            BottomNavItem.FavoriteScreen,
            BottomNavItem.SettingScreen
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.backgroundHome),
    ) {
        DashboardContent(
            currentDestination = currentDestination,
            modifier = Modifier.fillMaxSize()
        )

        DashboardBottomBar(
            navItems = navItems,
            currentDestination = currentDestination,
            onNavigate = { currentDestination = it },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun DashboardContent(
    currentDestination: String,
    modifier: Modifier = Modifier
) {
    AnimatedContent(
        modifier = modifier,
        targetState = currentDestination,
        transitionSpec = {
            fadeIn(tween(durationMillis = 200)) togetherWith fadeOut(tween(durationMillis = 200))
        },
        label = "DashboardContentAnimation"
    ) { destination ->
        when (destination) {
            BottomNavItem.OverviewScreen.path -> HomeScreen()
            BottomNavItem.FavoriteScreen.path -> FavoriteScreen()
            BottomNavItem.SearchScreen.path -> SearchScreen()
            BottomNavItem.SettingScreen.path -> EmptyScreen("Settings")
            else -> HomeScreen()
        }
    }
}

@Composable
private fun DashboardBottomBar(
    navItems: List<BottomNavItem>,
    currentDestination: String,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isOverlayVisible = currentDestination != BottomNavItem.SettingScreen.path

    Column(modifier = modifier) {
        // Gradient/Brush overlay
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .alpha(if (isOverlayVisible) 1f else 0f)
                .background(brush = AppTheme.colors.brush)
        )

        NavigationBar(
            containerColor = AppTheme.colors.backgroundHome,
            contentColor = Color.Transparent,
            tonalElevation = 0.dp
        ) {
            navItems.forEach { item ->
                val isSelected = currentDestination == item.path
                NavigationBarItem(
                    selected = isSelected,
                    onClick = { onNavigate(item.path) },
                    icon = {
                        BottomNavIcon(
                            icon = item.icon,
                            title = item.title,
                            isSelected = isSelected
                        )
                    },
                    label = {
                        BottomNavLabel(
                            title = item.title,
                            isSelected = isSelected
                        )
                    },
                    alwaysShowLabel = true,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                        selectedIconColor = AppTheme.colors.optionCategoryBackgroundEnabled,
                        unselectedIconColor = Color(0xFF878787),
                        selectedTextColor = AppTheme.colors.optionCategoryBackgroundEnabled,
                        unselectedTextColor = Color(0xFF878787)
                    )
                )
            }
        }
    }
}

@Composable
private fun BottomNavLabel(
    title: String,
    isSelected: Boolean
) {
    val color = if (isSelected) AppTheme.colors.optionCategoryBackgroundEnabled else Color(0xFF878787)
    val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = AppTheme.typography.commonRegularTextStyle.copy(
                color = color,
                fontSize = 10.sp,
                fontWeight = fontWeight
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .width(10.dp)
                .height(2.dp)
                .alpha(if (isSelected) 1f else 0f)
                .background(color, shape = RoundedCornerShape(50))
        )
    }
}

@Composable
private fun BottomNavIcon(
    icon: Int,
    title: String,
    isSelected: Boolean
) {
    val color = if (isSelected) AppTheme.colors.optionCategoryBackgroundEnabled else Color(0xFF878787)
    Icon(
        painter = painterResource(id = icon),
        contentDescription = title,
        tint = color
    )
}

@Composable
fun EmptyScreen(title: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        contentAlignment = Alignment.Center
    ) {
        Text(text = title)
    }
}
