package com.example.paytabs_demo_store_android.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.paytabs_demo_store_android.database.enities.FavouriteEntity

@Dao
interface FavDao {



    @Query("SELECT * FROM FavouriteEntity")
    suspend fun getFavouriteProducts(): List<FavouriteEntity>

    @Query("SELECT EXISTS (SELECT * FROM FavouriteEntity WHERE id =:id)")
    suspend fun productFavouriteExists(id: Int): Boolean

    @Insert()
    suspend fun addToFavourite(product: FavouriteEntity)

    @Delete()
    suspend fun deleteFromFavourite(product: FavouriteEntity)


}