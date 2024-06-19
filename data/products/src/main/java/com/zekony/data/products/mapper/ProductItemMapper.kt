package com.zekony.data.products.mapper

import com.zekony.domain.products.dto.DtoProductItem
import com.zekony.domain.products.entity.DbProductItem

fun DtoProductItem.mapToEntity(): DbProductItem =
    DbProductItem(
        gcode,
        cat_id,
        has_discount,
        img_preview,
        name,
        new,
        old_price,
        price,
        sale,
        units
    )