package com.example.easyfood.composble.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.easyfood.composble.component.BottomSheetContent
import com.example.easyfood.composble.component.CategoryComponent
import com.example.easyfood.composble.component.PopularMealComponent
import com.example.easyfood.model.Meal
import com.example.easyfood.ui.theme.Typography
import com.example.easyfood.ui.theme.primary
import com.example.easyfood.viewmodel.HomeScreenViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel
) {
    homeScreenViewModel.getRandomMeal()
    homeScreenViewModel.getPopularMeals()
    homeScreenViewModel.getCategories()
    val popularMeals = homeScreenViewModel.popularMeals.observeAsState(listOf())
    val categories = homeScreenViewModel.categoryList.observeAsState(listOf())
    val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    val clickedMealId = remember { mutableStateOf(52959) }
    homeScreenViewModel.getMealById(clickedMealId.value)
    val meal = homeScreenViewModel.meal.observeAsState(null).value
    ModalBottomSheetLayout(
        sheetContent = {
            BottomSheetContent(
                meal = meal ?: Meal(),
                onClick = {
                    navController.navigate("meal/${meal?.idMeal}")
                    coroutineScope.launch {
                        modalBottomSheetState.hide()
                    }
                }
            )
        },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topEnd = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Home",
                    style = Typography.h1,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .width(30.dp)
                        .height(40.dp)
                )
            }
            Spacer(Modifier.height(10.dp))
            Text(
                text = "What would you like to eat",
                style = Typography.h2
            )
            Spacer(Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = primary
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(homeScreenViewModel.randomMeal.observeAsState().value?.strMealThumb)
                        .crossfade(true)
                        .build(),
                    "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clickable {
                        navController.navigate("meal/${homeScreenViewModel.randomMeal.value?.idMeal}")
                    }
                )
            }
            Spacer(Modifier.height(18.dp))
            Text(
                text = "Over popular items",
                style = Typography.h2
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(popularMeals.value.size) { index ->
                    val myMeal = popularMeals.value[index]
                    PopularMealComponent(
                        url = myMeal.strMealThumb,
                        onClick = {
                            navController.navigate("meal/${myMeal.idMeal}")
                        },
                        onLongClick = {
                            clickedMealId.value = myMeal.idMeal
                            coroutineScope.launch {
                                modalBottomSheetState.show()
                            }
                        }
                    )
                } ?: items(1) {}
            }
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Categories",
                style = Typography.h2
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3), content = {
                        val categoriesL = categories.value
                        items(categoriesL.size) { index ->
                            val category = categoriesL[index]
                            CategoryComponent(
                                imgUrl = category.strCategoryThumb,
                                title = category.strCategory,
                                onClick = {
                                    navController.navigate("category_meals/${category.strCategory}")
                                }
                            )
                        }
                    },
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController(), HomeScreenViewModel())
}