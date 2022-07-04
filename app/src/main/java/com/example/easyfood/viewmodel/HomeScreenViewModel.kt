package com.example.easyfood.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyfood.model.*
import com.example.easyfood.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreenViewModel : ViewModel() {
    private val mutableRandomMeal = MutableLiveData<Meal>()
    val randomMeal: LiveData<Meal> = mutableRandomMeal

    private val mutablePopularMeals = MutableLiveData<List<PopularMeal>>()
    val popularMeals: LiveData<List<PopularMeal>> = mutablePopularMeals

    private val mutableCategoryList = MutableLiveData<List<Category>>()
    val categoryList: LiveData<List<Category>> = mutableCategoryList


    fun getRandomMeal() {
        viewModelScope.launch(Dispatchers.IO) {
            RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList> {
                override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                    if (response.body() != null) {
                        val randomMeal = response.body()!!.meals[0]
                        Log.d("randomMeal", "meal id ${randomMeal.idMeal} name ${randomMeal.strMeal}")
                        Log.d("randomMeal", "${randomMeal.strMealThumb}}")
                        mutableRandomMeal.value = randomMeal
                    }
                }

                override fun onFailure(call: Call<MealList>, t: Throwable) {
                    Log.d("randomImage", t.message.toString())
                }
            })
        }
    }

    fun getPopularMeals() {
        viewModelScope.launch(Dispatchers.IO) {
            RetrofitInstance.api.getPopularMeals().enqueue(object : Callback<PopularMealList> {
                override fun onResponse(
                    call: Call<PopularMealList>,
                    response: Response<PopularMealList>
                ) {
                    if (response.body() != null) {
                        val categories = response.body()!!.meals
                        mutablePopularMeals.value = categories
                    } else {
                        return
                    }
                }

                override fun onFailure(call: Call<PopularMealList>, t: Throwable) {
                    Log.d("popularMeals", t.message.toString())
                }
            })
        }
    }

    private val mutableMeal = MutableLiveData<Meal>()
    val meal: LiveData<Meal> = mutableMeal

    fun getMealById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            RetrofitInstance.api.getMealById(id).enqueue(object: Callback<MealList> {
                override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                    if (response.body() != null) {
                        mutableMeal.value = response.body()!!.meals[0]
                    }
                    else {
                        return
                    }
                }

                override fun onFailure(call: Call<MealList>, t: Throwable) {
                    Log.d("mealById", t.message.toString())
                }
            })
        }
    }

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryList> {
                override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                    if (response.body() != null) {
                        val categories = response.body()!!.categories
                        mutableCategoryList.value = categories
                    }
                }

                override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                    Log.d("categories", t.message.toString())
                }

            })
        }
    }
}