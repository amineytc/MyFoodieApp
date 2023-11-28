package com.amineaytac.myfoodieapp.data.repo

import android.util.Log
import com.amineaytac.myfoodieapp.data.model.CategoryResponse
import com.amineaytac.myfoodieapp.data.model.MealResponse
import com.amineaytac.myfoodieapp.data.source.MealApi
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor (
    private val mealApi : MealApi){

    suspend fun getRandomMeal() : Response<MealResponse>{
        val response = mealApi.getRandomMeal()
        if(response.isSuccessful){
            Log.d("testApp","success to connected to random meal")
            Log.d("testApp",response.code().toString())
        }else{
            Log.d("testApp","Failed to connected to random meal")
            Log.d("testApp",response.code().toString())
        }
        return response
    }

    suspend fun getCategoryMeal():Response<CategoryResponse>{
        val response = mealApi.getCategoriesHome()
        if(response.isSuccessful){
            Log.d("testApp","success to connected to category home")
            Log.d("testApp",response.code().toString())
        }else{
            Log.d("testApp","Failed to connected to category home")
            Log.d("testApp",response.code().toString())
        }
        return response
    }
}