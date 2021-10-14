package com.example.paytabs_demo_store_android.products.model.response

sealed class ProductResponse{
    class Success(val data:List<Product>):ProductResponse()
    class Failure(val msg:String):ProductResponse()
    object IOError : ProductResponse()
    object ServerError:ProductResponse()
}
