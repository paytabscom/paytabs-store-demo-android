package com.example.paytabs_demo_store_android.products.model.repo

import com.example.paytabs_demo_store_android.products.model.remote.ProductsService
import com.example.paytabs_demo_store_android.products.model.response.ProductResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val productsService: ProductsService) {

    suspend fun getAllProducts(): ProductResponse {
        return try {
            val data = productsService.getAllProducts()
            ProductResponse.Success(data)
        } catch (e: HttpException) {
            ProductResponse.Failure("Backend Error")
        } catch (e: IOException) {
            ProductResponse.IOError
        } catch (e: Exception) {
            ProductResponse.ServerError

        }
    }

    suspend fun getProductsByCategory(categoryName:String) : ProductResponse {
        return try {
            val data = productsService.getProductsByCategory(categoryName)
            ProductResponse.Success(data)
        } catch (e: HttpException) {
            ProductResponse.Failure("Backend Error")
        } catch (e: IOException) {
            ProductResponse.IOError
        } catch (e: Exception) {
            ProductResponse.ServerError

        }
    }
    suspend fun getProductsById() = productsService.getProductsById()


}