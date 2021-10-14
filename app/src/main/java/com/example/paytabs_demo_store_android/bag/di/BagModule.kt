package com.example.paytabs_demo_store_android.bag.di

import com.example.paytabs_demo_store_android.bag.model.repo.BagRepository
import com.example.paytabs_demo_store_android.database.PtDatabase
import com.example.paytabs_demo_store_android.database.dao.BagDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class BagModule {

    @Provides
    fun provideDao(db: PtDatabase): BagDao =
        db.bagDao()

    @Provides
    fun provideRepo(dao: BagDao) =
        BagRepository(dao)
}