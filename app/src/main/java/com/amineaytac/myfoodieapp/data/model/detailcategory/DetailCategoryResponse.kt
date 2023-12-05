package com.amineaytac.myfoodieapp.data.model.detailcategory

import com.amineaytac.myfoodieapp.data.model.detailcategory.DetailCategory
import com.google.gson.annotations.SerializedName

data class DetailCategoryResponse (
    @SerializedName("meals")
    val meals: List<DetailCategory>
)