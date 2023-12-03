package com.dkgtech.foodapp.retrofit

import com.dkgtech.foodapp.models.random_meal.MealModel
import retrofit2.Call
import retrofit2.http.GET

interface MealApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealModel>
}