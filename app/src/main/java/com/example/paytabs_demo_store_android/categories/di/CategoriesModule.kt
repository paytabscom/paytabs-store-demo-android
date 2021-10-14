package com.example.paytabs_demo_store_android.categories.di

import com.example.paytabs_demo_store_android.bag.model.repo.BagRepository
import com.example.paytabs_demo_store_android.categories.model.remote.CategoriesService
import com.example.paytabs_demo_store_android.categories.model.repo.CategoriesRepository
import com.example.paytabs_demo_store_android.database.PtDatabase
import com.example.paytabs_demo_store_android.database.dao.BagDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class CategoriesModule {

    @Provides
    fun provideWebServes(ret: Retrofit): CategoriesService =
        ret.create(CategoriesService::class.java)

    @Provides
    fun provideRepo(ws: CategoriesService) =
        CategoriesRepository(ws)
}