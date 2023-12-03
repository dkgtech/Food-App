package com.dkgtech.foodapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dkgtech.foodapp.fragments.HomeFragment
import com.dkgtech.foodapp.models.random_meal.Meal
import com.dkgtech.foodapp.models.random_meal.MealModel
import com.dkgtech.foodapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private var randomMealLiveData = MutableLiveData<Meal>()

    fun getRandomMeal() {

        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealModel> {
            override fun onResponse(call: Call<MealModel>, response: Response<MealModel>) {
                if (response.body() != null) {
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal

                } else {
                    return
                }
            }

            override fun onFailure(call: Call<MealModel>, t: Throwable) {
                Log.d(HomeFragment.TAG, "onFailure: ${t.message}")
            }

        })

    }

    fun observeRandomMealLiveData(): LiveData<Meal> {
        return randomMealLiveData
    }

}