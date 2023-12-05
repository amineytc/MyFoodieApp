package com.amineaytac.myfoodieapp.ui.category

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amineaytac.myfoodieapp.data.model.detailcategory.DetailCategory
import com.amineaytac.myfoodieapp.data.repo.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val mealRepo: MealRepository
):ViewModel(){

    private var _categoryStateFlow = MutableStateFlow<List<DetailCategory>>(emptyList())
    var categoryStateFlow : StateFlow<List<DetailCategory>> = _categoryStateFlow
    fun getCategory(categoryName:String){
        viewModelScope.launch {

             val response = mealRepo.getCategory(categoryName)

                 response.data?.let {
                     _categoryStateFlow.emit(it!!)
                 }
                 _categoryStateFlow.emit(response.data!!)
                //_categoryStateFlow.emit(response.body()!!.meals)
        }
    }
}