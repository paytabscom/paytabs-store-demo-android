package com.example.paytabs_demo_store_android.products.model.remote

import com.example.paytabs_demo_store_android.products.model.response.Product
import com.example.paytabs_demo_store_android.utils.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsService {

    // Get All products as a list of products
   @GET("Products")
    suspend fun getAllProducts() : List<Product>

    // Get all products under category of jewelery
    @GET("products/category/{category_name}")
    suspend fun getProductsByCategory(@Path("category_name") category: String) : List<Product>

    // Get specific product By Id
    @GET("products/1")

    suspend fun getProductsById() : Response<Product>

    companion object {
        var retrofitService: ProductsService? = null
        fun getInstance() : ProductsService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL.value)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ProductsService::class.java)
            }
           return retrofitService!!
     }



    }
}