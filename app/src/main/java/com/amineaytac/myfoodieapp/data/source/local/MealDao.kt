package com.amineaytac.myfoodieapp.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amineaytac.myfoodieapp.data.model.meal.Meal
import kotlinx.coroutines.flow.Flow
@Dao
interface MealDao{
    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun upsertMeal(meal: Meal)

    @Delete
    suspend fun deleteMeal(meal: Meal)

    @Query("SELECT * FROM mealInformation")
    fun getSavedMeal(): Flow<List<Meal>>
}