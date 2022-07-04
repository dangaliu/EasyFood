package com.example.easyfood.retrofit

import com.example.easyfood.model.CategoryList
import com.example.easyfood.model.PopularMealList
import com.example.easyfood.model.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealList>

    @GET("lookup.php")
    fun getMealById(@Query("i") id: Int): Call<MealList>

    @GET("filter.php")
    fun getPopularMeals(@Query("c") c: String = "Seafood"): Call<PopularMealList>

    @GET("categories.php")
    fun getCategories(): Call<CategoryList>

}