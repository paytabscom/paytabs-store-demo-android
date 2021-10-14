package com.example.paytabs_demo_store_android.product_details.di

import com.example.paytabs_demo_store_android.database.dao.BagDao
import com.example.paytabs_demo_store_android.database.dao.FavDao
import com.example.paytabs_demo_store_android.product_details.model.remote.ProductDetailsService
import com.example.paytabs_demo_store_android.product_details.model.repo.ProductDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ProductDetailsModule {

    @Provides
    fun provideWebService(retrofit: Retrofit): ProductDetailsService =
        retrofit.create(ProductDetailsService::class.java)

    @Provides
    fun provideRepo(webService: ProductDetailsService, bagDao: BagDao, favDao: FavDao) =
        ProductDetailsRepository(webService, bagDao, favDao)
}