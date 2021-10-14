package com.example.paytabs_demo_store_android.favourite.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paytabs_demo_store_android.database.enities.FavouriteEntity
import com.example.paytabs_demo_store_android.databinding.ProductFavItemBinding
import java.text.SimpleDateFormat
import java.util.*

class FavAdapter(private val onDeleteClicked: (product: FavouriteEntity) -> Unit ):
    RecyclerView.Adapter<FavProductsViewHolder>() {

    var productsList = mutableListOf<FavouriteEntity>()


    fun setProducts(products: List<FavouriteEntity>) {
        this.productsList = products.toMutableList()
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavProductsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductFavItemBinding.inflate(inflater, parent, false)
        return FavProductsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: FavProductsViewHolder, position: Int ) {

        val product = productsList[position]
        holder.binding.tvProductFavTitle.text = product.title
        holder.binding.tvProductFavDescription.text  = product.description
        holder.binding.tvProductFavPrice.text = product.price + "$"
        Glide.with(holder.itemView.context).load(product.image).into(holder.binding.ivProductFav)

        holder.binding.ivDelete.setOnClickListener{
            onDeleteClicked(product)
        }


    }

    override fun getItemCount(): Int {
        return productsList.size
    }



}

class FavProductsViewHolder(val binding: ProductFavItemBinding) : RecyclerView.ViewHolder(binding.root) {

}