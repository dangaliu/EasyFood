package com.example.easyfood.composble.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.easyfood.composble.component.CategoryMealComponent
import com.example.easyfood.composble.component.FavoriteMealComponent
import com.example.easyfood.viewmodel.FavoritesScreenViewModel

@Composable
fun FavoritesScreen(
    navController: NavHostController,
    favoritesScreenViewModel: FavoritesScreenViewModel
) {
    favoritesScreenViewModel.getFavoriteMeals()
    val meals = favoritesScreenViewModel.meals.observeAsState(listOf()).value
    Log.d("fav", meals.size.toString())
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            content = {
                items(meals.size) { index ->
                    val meal = meals[index]
                    FavoriteMealComponent(
                        meal = meal,
                        modifier = Modifier
                            .weight(1f)
                            .height(200.dp),
                        onCloseClick = {
                            favoritesScreenViewModel.deleteMeal(meal)
                        }
                    )
                }
            },
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
    }
}