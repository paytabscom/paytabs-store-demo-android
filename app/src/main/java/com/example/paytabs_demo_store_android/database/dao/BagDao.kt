package com.example.paytabs_demo_store_android.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.paytabs_demo_store_android.database.enities.FavouriteEntity
import com.example.paytabs_demo_store_android.database.enities.ProductEntity

@Dao
interface BagDao {
    @Query("SELECT * FROM ProductEntity")
    suspend fun getBagProducts(): List<ProductEntity>

    @Query("SELECT COUNT(id) FROM ProductEntity")
    fun getBagCount(): LiveData<Int>

    @Delete
    suspend fun delete(product: ProductEntity)

    @Query("DELETE FROM ProductEntity")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToBag(product: ProductEntity)

    @Query("UPDATE ProductEntity set count=count+1 WHERE id=:id")
    suspend fun increaseCount(id: Int)

    @Query("UPDATE ProductEntity set count=count-1 WHERE id=:id")
    suspend fun decreaseCount(id: Int)

    @Query("SELECT EXISTS (SELECT * FROM ProductEntity WHERE id =:id)")
    suspend fun productExists(id: Int): Boolean

}