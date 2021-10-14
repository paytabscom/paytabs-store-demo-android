package com.example.paytabs_demo_store_android.product_details.model.remote

import com.example.paytabs_demo_store_android.product_details.model.response.ProductDetails
import com.example.paytabs_demo_store_android.products.model.response.Product
import com.example.paytabs_demo_store_android.utils.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductDetailsService {

    // Get specific product By Id
    @GET("products/{product_id}")

    suspend fun getProductById(@Path("product_id") id:String) : Product

    companion object {
        var retrofitService: ProductDetailsService? = null
        fun getInstance() : ProductDetailsService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL.value)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ProductDetailsService::class.java)
            }
           return retrofitService!!
     }



    }
}