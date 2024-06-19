package com.zekony.domain.products.dto

data class DtoProductItem(
    val cat_id: Int,
    val gcode: Int,
    val has_discount: Int,
    val img_preview: String,
    val is_stocked: Int,
    val name: String,
    val new: Int,
    val old_price: Int,
    val pack: Int,
    val price: Int,
    val prior: Int,
    val qty: Int,
    val sale: Boolean,
    val stock: Int,
    val units: String
)