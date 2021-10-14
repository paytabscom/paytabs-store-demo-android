package com.example.paytabs_demo_store_android.orders.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.paytabs_demo_store_android.base_classes.BaseViewModel
import com.example.paytabs_demo_store_android.database.enities.OrdersEntity
import com.example.paytabs_demo_store_android.orders.model.repo.OrdersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class OrdersViewModel @Inject constructor(private val repo: OrdersRepo) : BaseViewModel(){

    val orders = MutableLiveData<List<OrdersEntity>>()
    fun getOrders(){
        viewModelScope.launch {
            orders.value=   repo.getOrders()
        }

    }

}