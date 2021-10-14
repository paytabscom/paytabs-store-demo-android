package com.example.paytabs_demo_store_android.categories.model.response

import com.example.paytabs_demo_store_android.products.model.response.Product

sealed class CategoriesResponse{
    class Success(val data:List<String>):CategoriesResponse()
    class Failure(val msg:String):CategoriesResponse()
    object IOError : CategoriesResponse()
    object ServerError:CategoriesResponse()
}