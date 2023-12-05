package com.amineaytac.myfoodieapp.data.model.detailcategory

import com.google.gson.annotations.SerializedName

data class DetailCategory (
    @SerializedName("idMeal")
    val idMeal:String,
    @SerializedName("strMeal")
    val strMeal:String,
    @SerializedName("strMealThumb")
    val strMealThumb:String
)