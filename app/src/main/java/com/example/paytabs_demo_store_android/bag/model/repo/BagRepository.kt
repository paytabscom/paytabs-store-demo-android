package com.example.paytabs_demo_store_android.bag.model.repo

import com.example.paytabs_demo_store_android.database.dao.BagDao
import com.example.paytabs_demo_store_android.database.enities.toProduct
import com.example.paytabs_demo_store_android.database.enities.toProductEntity
import com.example.paytabs_demo_store_android.products.model.response.Product
import javax.inject.Inject

class BagRepository @Inject constructor(private val dao: BagDao) {


    suspend fun getBagProducts(): List<Product> {
        return dao.getBagProducts().map { it.toProduct() }
    }

    suspend fun delete(product: Product) {
            dao.delete(product.toProductEntity())
        }

    suspend fun deleteAllProducts() {
        dao.deleteAll()
    }

    suspend fun increaseCount(productId: Int){
        dao.increaseCount(productId)
    }

    suspend fun decreaseCount( productId: Int){
        dao.decreaseCount(productId)
    }
}



