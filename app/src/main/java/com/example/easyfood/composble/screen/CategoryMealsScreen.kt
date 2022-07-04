package com.example.easyfood.composble.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.easyfood.composble.component.CategoryMealComponent
import com.example.easyfood.ui.theme.Typography
import com.example.easyfood.ui.theme.primary
import com.example.easyfood.viewmodel.CategoryMealsScreenViewModel

@Composable
fun CategoryMealsScreen(
    navController: NavHostController,
    category: String? = null,
    vm: CategoryMealsScreenViewModel
) {
    category?.let {
        vm.getCategoryMeals(it)
    }
    val meals = vm.categoryMeals.observeAsState(listOf()).value
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "$category: ${meals.size}",
            style = Typography.h2.copy(color = primary),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            content = {
                items(meals.size) { index ->
                    CategoryMealComponent(
                        meal = meals[index],
                        modifier = Modifier
                            .height(200.dp)
                            .weight(1f)
                    )
                }
            },
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
            )
    }
}