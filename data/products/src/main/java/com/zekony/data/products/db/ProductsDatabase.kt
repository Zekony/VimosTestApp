package com.zekony.data.products.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zekony.domain.products.entity.DbProductItem

@Database(
    entities = [DbProductItem::class],
    version = 1
)
abstract class ProductsDatabase : RoomDatabase() {

    abstract fun getProductsDao(): ProductsDao
}
