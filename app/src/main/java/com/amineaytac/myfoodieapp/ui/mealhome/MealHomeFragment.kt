package com.amineaytac.myfoodieapp.ui.mealhome

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.amineaytac.myfoodieapp.data.model.meal.Meal
import com.amineaytac.myfoodieapp.databinding.FragmentMealHomeBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealHomeFragment : Fragment() {

    private lateinit var binding : FragmentMealHomeBinding
    private val mealVM : MealHomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMealHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getmealInformation()
        observeGetMealInformationData()

        binding.btnFav.setOnClickListener{
            saveMeal?.let{meal-> mealVM.upsertMeal(meal)}
        }
    }

    private var saveMeal: Meal?=null
    private fun observeGetMealInformationData() {
        mealVM.getMealInformationLiveData.observe(requireActivity()) { data ->
            saveMeal=data
            binding.category.text = "Category: " + data.strCategory
            binding.location.text = "Location: " + data.strArea
            binding.second.text = data.strInstructions
        }
    }

    private fun getmealInformation() {
        val bundle : MealHomeFragmentArgs by navArgs()

        val mealId= bundle.mealId
        val mealName = bundle.mealName
        val mealThumb = bundle.mealThumb

        mealVM.getMealInformation(mealId!!)

        Glide.with(requireActivity())
            .load(mealThumb)
            .into(binding.mealImage)

        binding.collapsing.title=mealName

        Log.d("testApp",mealId)
    }
}