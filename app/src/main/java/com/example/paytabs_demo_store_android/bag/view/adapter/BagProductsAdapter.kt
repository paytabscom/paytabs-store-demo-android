package com.example.paytabs_demo_store_android.bag.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paytabs_demo_store_android.databinding.ProductBagItemBinding
import com.example.paytabs_demo_store_android.products.model.response.Product
import java.text.SimpleDateFormat
import java.util.*

class BagProductsAdapter(private val onDeleteClicked: (product: Product) -> Unit ,
                         private val onPlusClicked: (productId:Int) -> Unit ,
                         private val onMinusClicked: (productId:Int) -> Unit):
    RecyclerView.Adapter<BagProductsViewHolder>() {

    var productsList = mutableListOf<Product>()


    fun setProducts(products: List<Product>) {
        this.productsList = products.toMutableList()
        notifyDataSetChanged()
    }

    fun getTotalPrice(products: List<Product> = productsList): String {
        //todo iterative paradigm vs declarative paradigm
        val totalPrice = products.map { it.price.toFloat()* it.count }.sum()
        notifyDataSetChanged()
        return "%.2f".format(totalPrice)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagProductsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductBagItemBinding.inflate(inflater, parent, false)
        return BagProductsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: BagProductsViewHolder, position: Int ) {

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        val product = productsList[position]
        var newCount=product.count
        holder.binding.tvProductBagTitle.text = product.title
        holder.binding.tvProductBagDescription.text  = product.description
        holder.binding.tvProductBagPrice.text = product.price + "$"
        holder.binding.tvDeliveryDate.text = "Delivery: " + currentDate
        holder.binding.tvProductCount.text=product.count.toString()
        Glide.with(holder.itemView.context).load(product.image).into(holder.binding.ivProductBag)

        holder.binding.ivDelete.setOnClickListener{
            onDeleteClicked(product)
        }

        holder.binding.ivIncrease.setOnClickListener{
            newCount+= 1
            onPlusClicked(product.id)
            holder.binding.tvProductCount.text=newCount.toString()
        }

        holder.binding.ivDecrease.setOnClickListener{
            newCount-=1
            if(newCount>0){
                onMinusClicked(product.id)
                holder.binding.tvProductCount.text=newCount.toString()
            }
            else{
                onDeleteClicked(product)
            }

        }

    }

    override fun getItemCount(): Int {
        return productsList.size
    }



}

class BagProductsViewHolder(val binding: ProductBagItemBinding) : RecyclerView.ViewHolder(binding.root) {

}