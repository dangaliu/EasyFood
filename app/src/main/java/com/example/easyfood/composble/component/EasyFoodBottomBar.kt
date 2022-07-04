package com.example.easyfood.composble.component

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.easyfood.navigation.BottomBarItem
import com.example.easyfood.ui.theme.primary

@Composable
fun EasyFoodBottomBar(navController: NavHostController) {

    val items = listOf<BottomBarItem>(
        BottomBarItem.Home,
        BottomBarItem.Favorites,
        BottomBarItem.Categories
    )
    val navControllerBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navControllerBackStackEntry?.destination?.route
    BottomNavigation(
        backgroundColor = Color.White
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                selected = item.route == currentRoute,
                label = { Text(item.title) },
                icon = { Icon(item.iconRes, "") },
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                selectedContentColor = primary,
                unselectedContentColor = Color.Gray
            )
        }
    }
}