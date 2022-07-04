package com.example.easyfood.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.easyfood.composble.component.EasyFoodBottomBar
import com.example.easyfood.db.MealDatabase
import com.example.easyfood.navigation.Navigation
import com.example.easyfood.ui.theme.EasyFoodTheme
import com.example.easyfood.ui.theme.primary
import com.example.easyfood.viewmodel.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasyFoodTheme {
                App()
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun App() {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = primary
            )
        }
        val navController = rememberNavController()
        val homeScreenViewModel = ViewModelProvider(this)[HomeScreenViewModel::class.java]
        val context = LocalContext.current
        val mealDatabase = MealDatabase.getInstance(context)
        val mealScreenViewModelFactory = MealScreenViewModelFactory(mealDatabase)
        val mealScreenViewModel =
            ViewModelProvider(this, mealScreenViewModelFactory)[MealScreenViewModel::class.java]
        val categoryMealsScreenViewModel =
            ViewModelProvider(this)[CategoryMealsScreenViewModel::class.java]
        val favVmFactory = FavoritesScreenViewModelFactory(mealDatabase)
        val favoritesScreenViewModel = ViewModelProvider(this, favVmFactory)[FavoritesScreenViewModel::class.java]
        Scaffold(
            bottomBar = { EasyFoodBottomBar(navController = navController) }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = innerPadding.calculateBottomPadding() + 4.dp)
            ) {
                Navigation(
                    navController = navController,
                    homeScreenViewModel,
                    mealScreenViewModel,
                    categoryMealsScreenViewModel,
                    favoritesScreenViewModel
                )
            }
        }
    }
}

