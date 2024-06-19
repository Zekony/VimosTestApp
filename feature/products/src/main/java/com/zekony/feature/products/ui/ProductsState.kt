package com.zekony.feature.products.ui

import com.zekony.domain.products.entity.DbProductItem

data class ProductsState(
    val downloadState: DownloadState = DownloadState.Downloading,
    val productsList: List<DbProductItem> = emptyList(),
    val errorMessage: String? = null
)

enum class DownloadState{
    Downloading, Success, Error
}