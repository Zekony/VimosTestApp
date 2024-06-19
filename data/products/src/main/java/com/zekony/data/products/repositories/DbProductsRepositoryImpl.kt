package com.zekony.data.products.repositories

import com.zekony.data.products.db.ProductsDao
import com.zekony.domain.products.entity.DbProductItem
import com.zekony.domain.products.repositories.DbProductsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DbProductsRepositoryImpl(
    private val productsDao: ProductsDao,
    private val dispatcher: CoroutineContext
) : DbProductsRepository {

    override fun getProducts(): Flow<List<DbProductItem>> {
        return productsDao.getAllProducts().flowOn(dispatcher)
    }

    override suspend fun addProducts(products: List<DbProductItem>) {
        CoroutineScope(dispatcher).launch {
            productsDao.addProducts(products)
        }
    }

    override fun getProductByGcode(gcode: Int): Flow<DbProductItem> =
        productsDao.getProductByGcode(gcode).flowOn(dispatcher)
}