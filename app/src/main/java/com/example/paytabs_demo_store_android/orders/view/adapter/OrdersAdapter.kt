package com.example.paytabs_demo_store_android.orders.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paytabs_demo_store_android.database.enities.OrdersEntity
import com.example.paytabs_demo_store_android.databinding.OrdersItemBinding

class OrdersAdapter() : RecyclerView.Adapter<OrdersViewHolder>() {


    var orderslist = mutableListOf<OrdersEntity>()


    fun setOrders(orders: List<OrdersEntity>) {
        this.orderslist = orders.toMutableList()
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = OrdersItemBinding.inflate(inflater, parent, false)
        return OrdersViewHolder(binding)
    }


    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int ) {

        val order = orderslist[position]
        holder.binding.tvBillingName.text = order.name
        holder.binding.tvTime.text  = order.time
        holder.binding.tvAmount.text = order.amount.toString() + "$"


    }

    override fun getItemCount(): Int {
        return orderslist.size
    }



}



class OrdersViewHolder(val binding: OrdersItemBinding) : RecyclerView.ViewHolder(binding.root) {

}