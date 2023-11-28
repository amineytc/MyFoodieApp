package com.amineaytac.myfoodieapp.data.source

import com.amineaytac.myfoodieapp.data.model.CategoryResponse
import com.amineaytac.myfoodieapp.data.model.MealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    suspend fun getRandomMeal(): Response<MealResponse>

    @GET("categories.php")
    suspend fun getCategoriesHome():Response<CategoryResponse>

    @GET("lookup.php")
    suspend fun getMealInformation(
        @Query("i") mealId:String
    ): Response<MealResponse>
}