package com.amineaytac.myfoodieapp.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amineaytac.myfoodieapp.data.model.category.Category
import com.amineaytac.myfoodieapp.data.model.meal.Meal
import com.amineaytac.myfoodieapp.data.repo.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel(){

    private val _getRandomMealLiveData = MutableLiveData<Meal>()
    val getRandomMealLiveData : LiveData<Meal> get() = _getRandomMealLiveData

        @SuppressLint("SuspiciousIndentation")
        fun getRandomMeal(){
            viewModelScope.launch {
                val response = mealRepository.getRandomMeal()
                    response.data!!.let {
                        _getRandomMealLiveData.postValue(it?.get(0))
                    }
            }
        }

    private var _getCategoryStateFlow = MutableStateFlow<List<Category?>>(emptyList())
    var getCategoriesStateFlow : StateFlow<List<Category?>> = _getCategoryStateFlow

        fun getCategoriesHome(){
            viewModelScope.launch {
                val response = mealRepository.getCategoryMeal()
                    response.data!!.let{
                            _getCategoryStateFlow.emit(it)
                    }
            }
        }
}