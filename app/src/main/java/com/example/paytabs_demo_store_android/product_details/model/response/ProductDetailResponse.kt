package com.example.paytabs_demo_store_android.product_details.model.response

import com.example.paytabs_demo_store_android.products.model.response.Product

sealed class ProductDetailResponse{
    class Success(val data:Product):ProductDetailResponse()
    class Failure(val msg:String):ProductDetailResponse()
    object IOError : ProductDetailResponse()
    object ServerError:ProductDetailResponse()
}
