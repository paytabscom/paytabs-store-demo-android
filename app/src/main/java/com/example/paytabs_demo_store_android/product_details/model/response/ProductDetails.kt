package com.example.paytabs_demo_store_android.product_details.model.response


import com.google.gson.annotations.SerializedName

data class ProductDetails(
    @SerializedName("category")
    val category: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("title")
    val title: String?
)