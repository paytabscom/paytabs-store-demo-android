package com.example.paytabs_demo_store_android.categories.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.paytabs_demo_store_android.R
import com.example.paytabs_demo_store_android.databinding.CategoryItemBinding


class CategoriesAdapter(private val onCategoryClicked: (name: String) -> Unit) :
    RecyclerView.Adapter<CategoriesViewHolder>() {

    var categories = mutableListOf<String>()
    lateinit var context: Context

    fun setCategoriesList(list: List<String>) {
        this.categories = list.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = CategoryItemBinding.inflate(inflater, parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {

        val category = categories[position]
        holder.binding.tvCategoryName.text = category
        when (position) {
            0 -> {
                setImageDrawable(holder.binding.imCategoryImage, R.drawable.ic_electronic)
            }
            1 -> {
                setImageDrawable(holder.binding.imCategoryImage, R.drawable.ic_jewelry)
            }
            2 -> {
                setImageDrawable(holder.binding.imCategoryImage, R.drawable.ic_men_clothes)
            }
            3 -> {
                setImageDrawable(holder.binding.imCategoryImage, R.drawable.ic_womens_clothes)
            }
        }

        holder.itemView.setOnClickListener {
            onCategoryClicked(category)
        }


    }

    private fun setImageDrawable(iv: AppCompatImageView, drawableRes: Int) {
        iv.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                drawableRes
            )
        )
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}

class CategoriesViewHolder(val binding: CategoryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

}