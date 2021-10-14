package com.example.paytabs_demo_store_android.products.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paytabs_demo_store_android.products.model.repo.ProductsRepository

class ProductsViewModelFactory constructor(private val repository: ProductsRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
            ProductsViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}