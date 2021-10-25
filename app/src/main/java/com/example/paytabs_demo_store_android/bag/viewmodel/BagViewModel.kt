package com.example.paytabs_demo_store_android.bag.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.paytabs_demo_store_android.bag.model.repo.BagRepository
import com.example.paytabs_demo_store_android.base_classes.BaseViewModel
import com.example.paytabs_demo_store_android.products.model.response.Product
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class BagViewModel @Inject constructor(private val repo:BagRepository) :BaseViewModel(){

    //todo flow
    val bagProducts = MutableLiveData<List<Product>>()
    var bag:List<Product>?=null

    fun getBagProducts(){
        viewModelScope.launch {
         bagProducts.value=   repo.getBagProducts()
            bag=repo.getBagProducts()
        }

    }

    fun deleteBagProduct(product: Product){
        //todo transaction room ( )
        viewModelScope.launch {
            repo.delete(product)
            getBagProducts()
        }
    }

    fun deleteAllBagProducts(){
        viewModelScope.launch {
            repo.deleteAllProducts()
            getBagProducts()
        }
    }

    fun increaseCount(productId:Int) {
        viewModelScope.launch {
            repo.increaseCount(productId)
            getBagProducts()
        }
    }

    fun decreaseCount(productId:Int) {
        viewModelScope.launch {
            repo.decreaseCount(productId)
            getBagProducts()
        }
    }


}