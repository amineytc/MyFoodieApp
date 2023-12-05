package com.amineaytac.myfoodieapp.data.repo

import android.util.Log
import com.amineaytac.myfoodieapp.data.model.category.CategoryResponse
import com.amineaytac.myfoodieapp.data.model.detailcategory.DetailCategoryResponse
import com.amineaytac.myfoodieapp.data.model.meal.Meal
import com.amineaytac.myfoodieapp.data.model.meal.MealResponse
import com.amineaytac.myfoodieapp.data.source.local.MealDao
import com.amineaytac.myfoodieapp.data.source.remote.MealApi
import com.amineaytac.myfoodieapp.util.Resource
import retrofit2.Response
import javax.inject.Inject

class MealRepository @Inject constructor(
    private val mealApi: MealApi,
    private val db: MealDao
) {

    suspend fun getMealInformation(mealId:String) : Response<MealResponse>{

        Resource.Success(mealApi.getMealInformation(mealId).body()?.meals.orEmpty())
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

    suspend fun upsertMeal(meal: Meal){
        db.upsertMeal(meal)
    }
    suspend fun deleteMeal(meal: Meal){
        db.deleteMeal(meal)
    }

    val getMealSaved = db.getSavedMeal()

    suspend fun getRandomMeal() =try{
        Resource.Success(mealApi.getRandomMeal().body()?.meals.orEmpty())
    }catch (e:Exception){
        Resource.Error(e.message.orEmpty())
    }

    suspend fun getCategoryMeal() = try{
        Resource.Success(mealApi.getCategoriesHome().body()?.categories.orEmpty())
    }catch(e:Exception){
        Resource.Error(e.message.orEmpty())
    }

    suspend fun getCategory(categoryName:String)= try{
        Resource.Success(mealApi.getCategory(categoryName).body()?.meals.orEmpty())
    }catch(e:Exception){
        Resource.Error(e.message.orEmpty())
    }
}