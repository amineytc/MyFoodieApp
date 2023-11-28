package com.amineaytac.myfoodieapp.data.repo

import android.util.Log
import com.amineaytac.myfoodieapp.data.model.MealResponse
import com.amineaytac.myfoodieapp.data.source.MealApi
import retrofit2.Response
import javax.inject.Inject

class MealRepository @Inject constructor(private val mealApi:MealApi) {

    suspend fun getMealInformation(mealId:String) : Response<MealResponse>{

        val response = mealApi.getMealInformation(mealId)
        if(response.isSuccessful){
            Log.d("testApp", "success to connected to mealInformation")
            Log.d("testApp", response.code().toString())
        }else{
            Log.d("testApp", "failed to connected to mealInformation")
            Log.d("testApp", response.code().toString())
        }
        return response
    }

}