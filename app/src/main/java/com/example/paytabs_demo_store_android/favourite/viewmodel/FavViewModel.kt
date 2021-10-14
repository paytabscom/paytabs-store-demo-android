package com.example.paytabs_demo_store_android.favourite.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.paytabs_demo_store_android.base_classes.BaseViewModel
import com.example.paytabs_demo_store_android.database.enities.FavouriteEntity
import com.example.paytabs_demo_store_android.favourite.model.repo.FavRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class FavViewModel @Inject constructor(private val repo: FavRepo) : BaseViewModel(){

    val favProducts = MutableLiveData<List<FavouriteEntity>>()
    fun getFavProducts(){
        viewModelScope.launch {
            favProducts.value=   repo.getFavProducts()
        }

    }

    fun deleteFavProduct(product: FavouriteEntity){
        viewModelScope.launch {
            repo.deleteProduct(product)
            getFavProducts()
        }
    }



}