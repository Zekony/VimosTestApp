package com.zekony.domain.products.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zekony.utilities.Constants.PRODUCTS_TABLE_NAME

@Entity(tableName = PRODUCTS_TABLE_NAME)
data class DbProductItem(
    @PrimaryKey
    val gcode: Int,
    val catId: Int,
    val hasDiscount: Int,
    val imgPreview: String,
    val name: String,
    val new: Int,
    val oldPrice: Int,
    val price: Int,
    val sale: Boolean,
    val units: String
)
