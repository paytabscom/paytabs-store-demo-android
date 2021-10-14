package com.example.paytabs_demo_store_android.product_details.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.paytabs_demo_store_android.base_classes.BaseViewModel
import com.example.paytabs_demo_store_android.database.enities.FavouriteEntity
import com.example.paytabs_demo_store_android.product_details.model.repo.ProductDetailsRepository
import com.example.paytabs_demo_store_android.product_details.model.response.ProductDetailResponse
import com.example.paytabs_demo_store_android.products.model.response.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(private val mainRepository: ProductDetailsRepository) : BaseViewModel() {

    val productDetails = MutableLiveData<Product>()
    var isExist=false
    var isExistFav=false
    fun getProductDetails(productId: String) {
        viewModelScope.launch {
            loading.value = true
            val resp = mainRepository.getProductDetails(productId)
            loading.value = false
            when (resp) {
                is ProductDetailResponse.Failure -> errorMessage.value = resp.msg
                ProductDetailResponse.IOError -> networkErrorLD.value = true
                ProductDetailResponse.ServerError -> serverErrorLD.value = true
                is ProductDetailResponse.Success -> productDetails.value = resp.data
            }
        }
    }

    fun addToBag() {
        viewModelScope.launch {

                productDetails.value?.let { mainRepository.insert(it) }

        }
    }

    fun productExists(id: Int){
        viewModelScope.launch {

            isExist=mainRepository.exists(id)

        }
    }

    fun increaseCount(productId:Int) {
        viewModelScope.launch {
            mainRepository.increaseCount(productId)
        }
    }


    fun addToFav() {
        viewModelScope.launch {

            productDetails.value?.let { mainRepository.insertToFav(it) }

        }
    }

    fun productExistsFav(id: Int){
        viewModelScope.launch {

            isExistFav=mainRepository.existsFav(id)

        }
    }

    fun deleteFavProduct(product: Product){
        viewModelScope.launch {
            mainRepository.deleteProduct(product)
        }
    }

}