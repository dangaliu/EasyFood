package com.example.easyfood.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.easyfood.composble.screen.CategoriesScreen
import com.example.easyfood.composble.screen.FavoritesScreen
import com.example.easyfood.composble.screen.CategoryMealsScreen
import com.example.easyfood.composble.screen.HomeScreen
import com.example.easyfood.composble.screen.MealScreen
import com.example.easyfood.viewmodel.CategoryMealsScreenViewModel
import com.example.easyfood.viewmodel.FavoritesScreenViewModel
import com.example.easyfood.viewmodel.HomeScreenViewModel
import com.example.easyfood.viewmodel.MealScreenViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel,
    mealScreenViewModel: MealScreenViewModel,
    categoryMealsScreenViewModel: CategoryMealsScreenViewModel,
    favoritesScreenViewModel: FavoritesScreenViewModel
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarItem.Home.route
    ) {
        composable(route = BottomBarItem.Home.route) {
            HomeScreen(navController = navController, homeScreenViewModel)
        }
        composable(route = BottomBarItem.Favorites.route) {
            FavoritesScreen(
                navController = navController,
                favoritesScreenViewModel
            )
        }
        composable(route = BottomBarItem.Categories.route) {
            CategoriesScreen(navController = navController, homeScreenViewModel)
        }
        composable(route = "meal/{id}", arguments = listOf(
            navArgument("id") {
                type = NavType.IntType
            }
        )) { navBackStackEntry ->
            val args = navBackStackEntry.arguments
            val id = args?.getInt("id")
            MealScreen(navController = navController, mealScreenViewModel, id)
        }
        composable(route = "category_meals/{category}", arguments = listOf(
            navArgument("category") {
                type = NavType.StringType
            }
        )) { navBackStackEntry ->
            val args = navBackStackEntry.arguments
            val category = args?.getString("category")
            CategoryMealsScreen(
                navController = navController,
                category = category,
                vm = categoryMealsScreenViewModel
            )
        }
    }
}