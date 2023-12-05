package com.amineaytac.myfoodieapp.data.model.category


import com.amineaytac.myfoodieapp.data.model.category.Category
import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("categories")
    val categories: List<Category?>?
)