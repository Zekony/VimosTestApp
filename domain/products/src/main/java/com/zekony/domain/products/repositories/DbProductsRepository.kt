package com.zekony.domain.products.repositories

import com.zekony.domain.products.entity.DbProductItem
import kotlinx.coroutines.flow.Flow

interface DbProductsRepository {

    fun getProducts(): Flow<List<DbProductItem>>

    suspend fun addProducts(products: List<DbProductItem>)

     fun getProductByGcode(gcode: Int): Flow<DbProductItem>
}