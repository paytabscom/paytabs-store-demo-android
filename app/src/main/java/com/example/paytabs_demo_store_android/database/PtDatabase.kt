package com.example.paytabs_demo_store_android.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.paytabs_demo_store_android.database.dao.BagDao
import com.example.paytabs_demo_store_android.database.dao.FavDao
import com.example.paytabs_demo_store_android.database.dao.OrdersDao
import com.example.paytabs_demo_store_android.database.enities.FavouriteEntity
import com.example.paytabs_demo_store_android.database.enities.OrdersEntity
import com.example.paytabs_demo_store_android.database.enities.ProductEntity

@Database(entities = arrayOf(ProductEntity::class , FavouriteEntity::class , OrdersEntity::class), version = 3)
abstract class PtDatabase : RoomDatabase() {

    abstract fun bagDao(): BagDao
    abstract fun favDao(): FavDao
    abstract fun ordersDao():OrdersDao

}
