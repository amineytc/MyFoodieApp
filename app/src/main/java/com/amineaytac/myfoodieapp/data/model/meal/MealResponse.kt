package com.amineaytac.myfoodieapp.data.model.meal

import com.google.gson.annotations.SerializedName
data class MealResponse(
    @SerializedName("meals")
    val meals: List<Meal>?
)