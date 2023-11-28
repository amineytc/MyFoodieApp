package com.amineaytac.myfoodieapp.data.model


import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("categories")
    val categories: List<Category?>?
)