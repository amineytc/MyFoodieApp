package com.amineaytac.myfoodieapp.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amineaytac.myfoodieapp.data.model.meal.Meal
import com.amineaytac.myfoodieapp.databinding.ItemFavoriteBinding
import com.bumptech.glide.Glide

class FavoriteAdapter():RecyclerView.Adapter<FavoriteAdapter.ViewHolder>(){

    private val diffUtil= object : DiffUtil.ItemCallback<Meal>(){

        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal==newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,diffUtil)

    inner class ViewHolder(val binding : ItemFavoriteBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        Glide.with(holder.itemView)
            .load(data.strMealThumb)
            .into(holder.binding.favoriteImage)
        holder.binding.favoriteText.text=data.strMeal
    }
}