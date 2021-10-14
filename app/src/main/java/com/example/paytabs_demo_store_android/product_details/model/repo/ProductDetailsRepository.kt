package com.example.paytabs_demo_store_android.product_details.model.repo

import com.example.paytabs_demo_store_android.database.dao.BagDao
import com.example.paytabs_demo_store_android.database.dao.FavDao
import com.example.paytabs_demo_store_android.database.enities.FavouriteEntity
import com.example.paytabs_demo_store_android.database.enities.ProductEntity
import com.example.paytabs_demo_store_android.database.enities.toFavouriteEntity
import com.example.paytabs_demo_store_android.database.enities.toProductEntity
import com.example.paytabs_demo_store_android.product_details.model.remote.ProductDetailsService
import com.example.paytabs_demo_store_android.product_details.model.response.ProductDetailResponse
import com.example.paytabs_demo_store_android.products.model.response.Product
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductDetailsRepository @Inject constructor(private val service: ProductDetailsService, val bagDao : BagDao, val favDao: FavDao ) {

    suspend fun getProductDetails(productId: String): ProductDetailResponse {
        return try {
            val data = service.getProductById(productId)
            ProductDetailResponse.Success(data)
        } catch (e: HttpException) {
            ProductDetailResponse.Failure("Backend Error")
        } catch (e: IOException) {
            ProductDetailResponse.IOError
        } catch (e: Exception) {
            ProductDetailResponse.ServerError

        }
    }

    // todo insert function
    suspend fun insert(product: Product){
        product.count=1
       var productentity:ProductEntity = product.toProductEntity()
        bagDao.addToBag(productentity)
    }

    suspend fun exists(id:Int):Boolean{
        return bagDao.productExists(id)
    }

    suspend fun increaseCount(productId: Int){
        bagDao.increaseCount(productId)
    }

    suspend fun insertToFav(product: Product){
        var favProductEntity:FavouriteEntity = product.toFavouriteEntity()
        favDao.addToFavourite(favProductEntity)
    }

    suspend fun existsFav(id:Int):Boolean{
        return favDao.productFavouriteExists(id)
    }

    suspend fun deleteProduct(product: Product) {
        favDao.deleteFromFavourite(product.toFavouriteEntity())
    }

}