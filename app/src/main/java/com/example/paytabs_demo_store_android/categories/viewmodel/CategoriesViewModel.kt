package com.example.paytabs_demo_store_android.categories.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paytabs_demo_store_android.base_classes.BaseViewModel
import com.example.paytabs_demo_store_android.categories.model.repo.CategoriesRepository
import com.example.paytabs_demo_store_android.categories.model.response.CategoriesResponse
import com.example.paytabs_demo_store_android.products.model.response.Product
import com.example.paytabs_demo_store_android.products.model.response.ProductResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel

class CategoriesViewModel  @Inject constructor(private val categoryRepository: CategoriesRepository) :  BaseViewModel() {

    val categoriesList = MutableLiveData<List<String>>()

    fun getCategories() {
        viewModelScope.launch {
            loading.value = true
            val resp = categoryRepository.getCategories()
            loading.value = false
            when (resp) {
                is CategoriesResponse.Failure -> errorMessage.value = resp.msg
                CategoriesResponse.IOError -> networkErrorLD.value = true
                CategoriesResponse.ServerError -> serverErrorLD.value = true
                is CategoriesResponse.Success -> categoriesList.value = resp.data
            }
        }
    }
}