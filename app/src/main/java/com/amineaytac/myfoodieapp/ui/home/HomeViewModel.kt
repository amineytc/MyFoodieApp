package com.amineaytac.myfoodieapp.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amineaytac.myfoodieapp.data.model.Category
import com.amineaytac.myfoodieapp.data.model.Meal
import com.amineaytac.myfoodieapp.data.repo.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel(){

    private val _getRandomMealLiveData = MutableLiveData<Meal>()
    val getRandomMealLiveData : LiveData<Meal> get() = _getRandomMealLiveData

        @SuppressLint("SuspiciousIndentation")
        fun getRandomMeal(){
            viewModelScope.launch {
                val response = homeRepository.getRandomMeal()
                    response.body()!!.meals.let {
                        _getRandomMealLiveData.postValue(it?.get(0))
                    }
            }
        }

    private var _getCategoryStateFlow = MutableStateFlow<List<Category?>>(emptyList())
    var getCategoriesStateFlow : StateFlow<List<Category?>> = _getCategoryStateFlow

        fun getCategoriesHome(){
            viewModelScope.launch {
                val response = homeRepository.getCategoryMeal()
                if(response.isSuccessful){
                    response.body()?.categories.let{
                        if(it!=null){
                            _getCategoryStateFlow.emit(it)
                        }
                    }
                }
            }
        }
}