package com.example.paytabs_demo_store_android.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paytabs_demo_store_android.database.enities.OrdersEntity
import com.example.paytabs_demo_store_android.database.enities.ProductEntity

@Dao
interface OrdersDao {

    @Query("SELECT * FROM OrdersEntity")
    suspend fun getOrderItems(): List<OrdersEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addOrder(order: OrdersEntity)
}