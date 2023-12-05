package com.amineaytac.myfoodieapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amineaytac.myfoodieapp.R
import com.amineaytac.myfoodieapp.databinding.FragmentHomeBinding
import com.amineaytac.myfoodieapp.ui.category.CategoryFragment
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val homeVM : HomeViewModel by viewModels()
    private val homeCategoryAdapter by lazy {HomeCategoryAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRandomMeal()
        getCategories()
        setCategoryHomeRecView()
        onCategoryItemClick()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun onCategoryItemClick() {
        homeCategoryAdapter.onCategoryCliclk={data->
            val fragment = CategoryFragment()
            val bundle = Bundle()
                bundle.putString("categoryName",data.strCategory)
                fragment.arguments=bundle

            findNavController().navigate(R.id.action_homeFragment_to_categoryFragment,bundle)
        }
    }

    private fun setCategoryHomeRecView() {
        binding.categoriesRecyc.apply {
            layoutManager=GridLayoutManager(context,2, RecyclerView.VERTICAL,false)
            adapter = homeCategoryAdapter
        }
    }

    private fun getCategories() {
        homeVM.getCategoriesHome()
        lifecycleScope.launch {
            homeVM.getCategoriesStateFlow.collect{data->
                homeCategoryAdapter.differ.submitList(data)
            }
        }
    }

    private fun getRandomMeal() {
        homeVM.getRandomMeal()
        homeVM.getRandomMealLiveData.observe(viewLifecycleOwner){data ->
            if(data!=null){
                Glide.with(this)
                    .load(data.strMealThumb)
                    .into(binding.randomImage)

                binding.randomImage.setOnClickListener {
                    val pass = HomeFragmentDirections.actionHomeFragmentToMealHomeFragment(
                        data.idMeal,
                        data.strMeal,
                        data.strMealThumb)

                    Navigation.findNavController(binding.randomImage).navigate(pass)
                }
            }
        }
    }
}