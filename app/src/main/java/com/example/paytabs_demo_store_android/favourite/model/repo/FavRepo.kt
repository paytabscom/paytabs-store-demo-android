package com.example.paytabs_demo_store_android.favourite.model.repo

import com.example.paytabs_demo_store_android.database.dao.FavDao
import com.example.paytabs_demo_store_android.database.enities.FavouriteEntity
import javax.inject.Inject

class FavRepo @Inject constructor(val favDao: FavDao ) {


    suspend fun getFavProducts(): List<FavouriteEntity> {
        return favDao.getFavouriteProducts()
    }

    suspend fun deleteProduct(product: FavouriteEntity) {
        favDao.deleteFromFavourite(product)
    }
}