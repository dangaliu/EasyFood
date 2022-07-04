package com.example.easyfood.composble.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.easyfood.composble.component.CategoryComponent
import com.example.easyfood.viewmodel.HomeScreenViewModel

@Composable
fun CategoriesScreen(
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel
) {
    homeScreenViewModel.getCategories()
    val categories = homeScreenViewModel.categoryList.observeAsState(listOf()).value
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            content = {
                items(categories.size) { index ->
                    val category = categories[index]
                    CategoryComponent(
                        imgUrl = category.strCategoryThumb,
                        title = category.strCategory,
                    )
                }
            }
        )
    }
}