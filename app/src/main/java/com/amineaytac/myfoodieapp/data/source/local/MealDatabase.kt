package com.amineaytac.myfoodieapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.amineaytac.myfoodieapp.data.model.meal.Meal
@Database(entities = [Meal::class],version=1)
@TypeConverters(MealTypeConverter::class)
abstract class MealDatabase : RoomDatabase(){
    abstract fun getMealFromDao(): MealDao
}