package com.example.paytabs_demo_store_android.products.di

import com.example.paytabs_demo_store_android.products.model.remote.ProductsService
import com.example.paytabs_demo_store_android.products.model.repo.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
 class ProductsModule {

    @Provides
    fun provideWebService(retrofit: Retrofit): ProductsService =
        retrofit.create(ProductsService::class.java)

    @Provides
    fun provideRepo(webService: ProductsService) =
        ProductsRepository(webService)
}