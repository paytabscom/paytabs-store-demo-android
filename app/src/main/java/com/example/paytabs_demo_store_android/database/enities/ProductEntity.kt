package com.example.paytabs_demo_store_android.database.enities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.paytabs_demo_store_android.product_details.model.response.ProductDetails
import com.example.paytabs_demo_store_android.products.model.response.Product

@Entity
data class ProductEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "count") val count:Int
)

fun ProductEntity.toProduct(): Product {
    return Product(id, title, price, description, category, image , count)
}

fun Product.toProductEntity(): ProductEntity {
    return ProductEntity(id, title, price, description, category, image , count)
}
