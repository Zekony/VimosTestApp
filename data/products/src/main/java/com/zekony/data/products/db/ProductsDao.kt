package com.zekony.data.products.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zekony.domain.products.entity.DbProductItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Query("SELECT * FROM PRODUCTS_TABLE_NAME")
    fun getAllProducts(): Flow<List<DbProductItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProducts(items : List<DbProductItem>)

    @Query("SELECT * FROM PRODUCTS_TABLE_NAME WHERE gcode = :gcode")
    fun getProductByGcode(gcode: Int): Flow<DbProductItem>
}