package com.example.easyfood.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyfood.db.MealDatabase
import com.example.easyfood.model.Meal
import com.example.easyfood.model.MealList
import com.example.easyfood.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealScreenViewModel(
    private val mealDatabase: MealDatabase
): ViewModel() {
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

    fun insertMeal(meal: Meal) {
        viewModelScope.launch {
            mealDatabase.mealDao().upsert(meal)
        }
    }

}