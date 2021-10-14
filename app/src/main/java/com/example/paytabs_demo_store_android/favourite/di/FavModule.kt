package com.example.paytabs_demo_store_android.favourite.di

import com.example.paytabs_demo_store_android.database.PtDatabase
import com.example.paytabs_demo_store_android.database.dao.FavDao
import com.example.paytabs_demo_store_android.favourite.model.repo.FavRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FavModule {

    @Provides
    fun provideDao(db: PtDatabase): FavDao =
        db.favDao()

    @Provides
    fun provideRepo(dao: FavDao) =
        FavRepo(dao)
}