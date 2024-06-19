package com.zekony.data.products.repositories

import android.content.Context
import com.zekony.data.products.mapper.mapToEntity
import com.zekony.data.products.network.ApiService
import com.zekony.data.products.network.handleException
import com.zekony.domain.products.entity.DbProductItem
import com.zekony.domain.products.repositories.ApiProductsRepository
import com.zekony.domain.products.util.Resource
import com.zekony.resources.R

class ApiProductsRepositoryImpl(
    private val apiService: ApiService,
    private val context: Context
): ApiProductsRepository {

    override suspend fun fetchProductsList(): Resource<List<DbProductItem>> {
        return try {
            val response = apiService.fetchProductsList()
            if (response != null) Resource.Success(response.map { it.mapToEntity() }) else Resource.Error(context.getString(R.string.empty_error_message))
        } catch (e: Exception) {
            handleException(e, context)
        }
    }
}