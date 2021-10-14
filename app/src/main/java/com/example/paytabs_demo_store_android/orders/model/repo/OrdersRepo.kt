package com.example.paytabs_demo_store_android.orders.model.repo


import com.example.paytabs_demo_store_android.database.dao.OrdersDao
import com.example.paytabs_demo_store_android.database.enities.OrdersEntity
import javax.inject.Inject

class OrdersRepo @Inject constructor(val ordersDao: OrdersDao) {


    suspend fun getOrders(): List<OrdersEntity> {
        return ordersDao.getOrderItems()
    }

}