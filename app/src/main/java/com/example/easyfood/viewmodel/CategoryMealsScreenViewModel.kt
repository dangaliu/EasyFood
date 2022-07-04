package com.example.easyfood.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyfood.model.PopularMeal
import com.example.easyfood.model.PopularMealList
import com.example.easyfood.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealsScreenViewModel : ViewModel() {

    private val mutableCategoryMeals = MutableLiveData<List<PopularMeal>>()
    val categoryMeals: LiveData<List<PopularMeal>> = mutableCategoryMeals

    fun getCategoryMeals(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            RetrofitInstance.api.getPopularMeals(c = category)
                .enqueue(object : Callback<PopularMealList> {
                    override fun onResponse(
                        call: Call<PopularMealList>,
                        response: Response<PopularMealList>
                    ) {
                        if (response.body() != null) {
                            val meals = response.body()!!.meals
                            mutableCategoryMeals.value = meals
                        }
                    }

                    override fun onFailure(call: Call<PopularMealList>, t: Throwable) {
                        Log.d("categoryMeals", t.message.toString())
                    }

                })
        }
    }
}