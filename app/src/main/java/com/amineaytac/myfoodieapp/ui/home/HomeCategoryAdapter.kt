package com.amineaytac.myfoodieapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amineaytac.myfoodieapp.data.model.Category
import com.amineaytac.myfoodieapp.databinding.ItemCategoriesBinding
import com.bumptech.glide.Glide

class HomeCategoryAdapter():RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder>(){

    class ViewHolder(val binding :ItemCategoriesBinding):RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Category>(){

        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.idCategory ==newItem.idCategory
        }
        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCategoriesBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]

        Glide.with(holder.itemView)
            .load(data.strCategoryThumb)
            .into(holder.binding.categoryMeal)

        holder.binding.categoryName.text=data.strCategory

        holder.itemView.setOnClickListener {
        }
    }
}