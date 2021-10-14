package com.example.paytabs_demo_store_android.products.model.response

import com.google.gson.annotations.SerializedName


data class Product(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: String,
    @SerializedName("description") val description: String,
    @SerializedName("category") val category: String,
    @SerializedName("image") val image: String,
    var count: Int
)
