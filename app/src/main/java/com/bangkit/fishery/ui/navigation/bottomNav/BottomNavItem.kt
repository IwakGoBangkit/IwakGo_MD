package com.bangkit.fishery.ui.navigation.bottomNav

import androidx.compose.ui.graphics.painter.Painter
import com.bangkit.fishery.ui.navigation.Screen

data class BottomNavItem(
    val title: String,
    val icon: Painter,
    val screen: Screen
)