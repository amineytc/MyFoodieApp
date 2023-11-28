package com.amineaytac.myfoodieapp.ui.mealhome

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amineaytac.myfoodieapp.data.model.Meal
import com.amineaytac.myfoodieapp.data.repo.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealHomeViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel(){

    private val _getMealInformationLiveData = MutableLiveData<Meal>()
    val getMealInformationLiveData : LiveData<Meal> = _getMealInformationLiveData

    fun getMealInformation(mealId:String){
        viewModelScope.launch {
            try{
                val response = mealRepository.getMealInformation(mealId)
                if(response.isSuccessful){
                    _getMealInformationLiveData.value = response.body()!!.meals?.get(0)
                }
            }catch (t:Throwable){
                Log.d("testApp",t.message.toString())
            }
        }
    }


}