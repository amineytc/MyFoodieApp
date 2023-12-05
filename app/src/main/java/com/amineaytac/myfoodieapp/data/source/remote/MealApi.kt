package com.amineaytac.myfoodieapp.data.source.remote

import com.amineaytac.myfoodieapp.data.model.category.CategoryResponse
import com.amineaytac.myfoodieapp.data.model.detailcategory.DetailCategoryResponse
import com.amineaytac.myfoodieapp.data.model.meal.MealResponse
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

    @GET("filter.php")
    suspend fun getCategory(@Query("c") categoryName:String):Response<DetailCategoryResponse>
}