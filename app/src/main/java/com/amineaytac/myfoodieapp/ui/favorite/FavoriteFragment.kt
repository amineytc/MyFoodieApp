package com.amineaytac.myfoodieapp.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amineaytac.myfoodieapp.R
import com.amineaytac.myfoodieapp.databinding.FragmentFavoriteBinding
import com.amineaytac.myfoodieapp.ui.mealhome.MealHomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding : FragmentFavoriteBinding
    private val mealVM : MealHomeViewModel by viewModels()
    private lateinit var favoriteAdapter :FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpFavoriteRecView()
        getSaveData()
    }

    private fun getSaveData() {
        lifecycleScope.launchWhenStarted{
            mealVM.getSavedMeal().collect(){
                favoriteAdapter.differ.submitList(it)
            }
        }
    }

    private fun setUpFavoriteRecView() {
        favoriteAdapter= FavoriteAdapter()
        binding.favoriteRecyc.apply {
            layoutManager=GridLayoutManager(context,2,RecyclerView.VERTICAL,false)
            adapter=favoriteAdapter
        }
    }
}