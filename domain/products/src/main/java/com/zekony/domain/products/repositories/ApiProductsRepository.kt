package com.zekony.domain.products.repositories

import com.zekony.domain.products.entity.DbProductItem
import com.zekony.domain.products.util.Resource

interface ApiProductsRepository {

    suspend fun fetchProductsList(): Resource<List<DbProductItem>>
}