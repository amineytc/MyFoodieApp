package com.amineaytac.myfoodieapp.ui.category

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amineaytac.myfoodieapp.R
import com.amineaytac.myfoodieapp.databinding.FragmentCategoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private lateinit var binding : FragmentCategoryBinding
    private lateinit var categoryName:String
    private val categoryVM : CategoryViewModel by viewModels()
    private  val categoryAdapter : CategoryAdapter by lazy { CategoryAdapter()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDataByBundle()
        getCategoryInfo()
        setCategoryRec()
    }

    private fun setCategoryRec() {
        binding.detailCategoryRec.apply {
            layoutManager=GridLayoutManager(context, 2,
                RecyclerView.VERTICAL,false)
            adapter= categoryAdapter
        }
    }

    private fun getCategoryInfo() {
        lifecycleScope.launchWhenStarted {
            categoryVM.getCategory(categoryName)
            categoryVM.categoryStateFlow.collect{data->
                categoryAdapter.differ.submitList(data)
            }
        }
    }

    private fun getDataByBundle() {
        val data = arguments
        if(data!=null){
            categoryName=data.getString("categoryName").toString()
        }
    }
}