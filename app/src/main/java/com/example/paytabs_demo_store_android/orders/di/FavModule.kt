package com.example.paytabs_demo_store_android.orders.di

import com.example.paytabs_demo_store_android.database.PtDatabase
import com.example.paytabs_demo_store_android.database.dao.FavDao
import com.example.paytabs_demo_store_android.database.dao.OrdersDao
import com.example.paytabs_demo_store_android.favourite.model.repo.FavRepo
import com.example.paytabs_demo_store_android.orders.model.repo.OrdersRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class OrdersModule {

    @Provides
    fun provideDao(db: PtDatabase): OrdersDao =
        db.ordersDao()

    @Provides
    fun provideRepo(dao: OrdersDao) =
        OrdersRepo(dao)
}