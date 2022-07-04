package com.example.easyfood.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItem(
    val title: String,
    val iconRes: ImageVector,
    val route: String
) {
    object Home : BottomBarItem("Home", Icons.Filled.Home, "home")
    object Favorites : BottomBarItem("Favorites", Icons.Filled.Favorite, "favorites")
    object Categories : BottomBarItem("Categories", Icons.Default.ShoppingCart, "categories")
}