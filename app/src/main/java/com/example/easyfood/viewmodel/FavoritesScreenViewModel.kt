package com.example.easyfood.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyfood.db.MealDatabase
import com.example.easyfood.model.Meal
import kotlinx.coroutines.launch

class FavoritesScreenViewModel(private val mealDatabase: MealDatabase): ViewModel() {

    var meals: LiveData<List<Meal>> = object: LiveData<List<Meal>>() {}

    fun getFavoriteMeals() {
        meals = mealDatabase.mealDao().getAllMeals()
    }

    fun deleteMeal(meal: Meal) {
        viewModelScope.launch {
            mealDatabase.mealDao().delete(meal)
        }
    }
}