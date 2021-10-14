package com.example.paytabs_demo_store_android.database.enities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.paytabs_demo_store_android.products.model.response.Product

@Entity
data class FavouriteEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "image") val image: String
)

fun Product.toFavouriteEntity(): FavouriteEntity {
    return FavouriteEntity(id, title, price, description, category, image)
}
