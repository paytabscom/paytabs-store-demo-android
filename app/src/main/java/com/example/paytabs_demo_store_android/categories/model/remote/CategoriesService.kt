package com.example.paytabs_demo_store_android.categories.model.remote

import com.example.paytabs_demo_store_android.utils.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CategoriesService {


    // Get all Categories
    @GET("products/categories")
    suspend fun getCategories(): List<String>

    companion object {
        var retrofitService: CategoriesService? = null
        fun getInstance(): CategoriesService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL.value)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(CategoriesService::class.java)
            }
            return retrofitService!!
        }
    }
}