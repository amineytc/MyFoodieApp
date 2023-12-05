package com.amineaytac.myfoodieapp.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amineaytac.myfoodieapp.data.model.detailcategory.DetailCategory
import com.amineaytac.myfoodieapp.databinding.ItemFavoriteBinding
import com.bumptech.glide.Glide
class CategoryAdapter():RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    inner class ViewHolder(val binding : ItemFavoriteBinding):RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<DetailCategory>(){

        override fun areItemsTheSame(oldItem: DetailCategory, newItem: DetailCategory): Boolean {
            return oldItem.idMeal ==newItem.idMeal
        }
        override fun areContentsTheSame(oldItem: DetailCategory, newItem: DetailCategory): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,diffUtil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun getItemCount(): Int =differ.currentList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        Glide.with(holder.itemView)
            .load(data.strMealThumb)
            .into(holder.binding.favoriteImage)
        holder.binding.favoriteText.text=data.strMeal
    }
}