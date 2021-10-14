package com.example.paytabs_demo_store_android.products.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paytabs_demo_store_android.products.model.response.Product
import com.example.paytabs_demo_store_android.databinding.ProductItemBinding

class ProductAdapter(private val onProductClicked: (name: String) -> Unit) :
    RecyclerView.Adapter<ProductsViewHolder>() {

    var productsList = mutableListOf<Product>()

    fun setProducts(products: List<Product>) {
        this.productsList = products.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {

        //getting the product of the specified position

        val product = productsList[position]
        holder.binding.tvTitle.text = product.title
        holder.binding.tvPrice.text  = product.price + "$"

        Glide.with(holder.itemView.context).load(product.image).into(holder.binding.imageview)

        holder.itemView.setOnClickListener {
            onProductClicked(product.id.toString())
        }


    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}

class ProductsViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {

}