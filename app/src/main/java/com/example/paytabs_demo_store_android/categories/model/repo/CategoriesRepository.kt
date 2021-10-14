package com.example.paytabs_demo_store_android.categories.model.repo

import com.example.paytabs_demo_store_android.categories.model.remote.CategoriesService
import com.example.paytabs_demo_store_android.categories.model.response.CategoriesResponse
import com.example.paytabs_demo_store_android.products.model.response.ProductResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoriesRepository @Inject constructor(private val categoriesService: CategoriesService) {

    suspend fun getCategories(): CategoriesResponse {

        return try {
            val data = categoriesService.getCategories()
            CategoriesResponse.Success(data)
        } catch (e: HttpException) {
            CategoriesResponse.Failure("Backend Error")
        } catch (e: IOException) {
            CategoriesResponse.IOError
        } catch (e: Exception) {
            CategoriesResponse.ServerError

        }
    }

}