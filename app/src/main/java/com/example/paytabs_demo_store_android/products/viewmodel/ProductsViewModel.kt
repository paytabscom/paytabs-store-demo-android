package com.example.paytabs_demo_store_android.products.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.paytabs_demo_store_android.base_classes.BaseViewModel
import com.example.paytabs_demo_store_android.products.model.repo.ProductsRepository
import com.example.paytabs_demo_store_android.products.model.response.Product
import com.example.paytabs_demo_store_android.products.model.response.ProductResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val mainRepository: ProductsRepository
) :
    BaseViewModel() {

    val productList = MutableLiveData<List<Product>>()

    fun getAllProducts() {
        viewModelScope.launch {
            loading.value = true
            val resp = mainRepository.getAllProducts()
            loading.value = false
            when (resp) {
                is ProductResponse.Failure -> errorMessage.value = resp.msg
                ProductResponse.IOError -> networkErrorLD.value = true
                ProductResponse.ServerError -> serverErrorLD.value = true
                is ProductResponse.Success -> productList.value = resp.data
            }
        }
    }

    fun getProductsByCategory(categoryName: String) {
        viewModelScope.launch {
            loading.value = true
            val resp = mainRepository.getProductsByCategory(categoryName)
            loading.value = false
            when (resp) {
                is ProductResponse.Failure -> errorMessage.value = resp.msg
                ProductResponse.IOError -> networkErrorLD.value = true
                ProductResponse.ServerError -> serverErrorLD.value = true
                is ProductResponse.Success -> productList.value = resp.data
            }
        }
    }

}