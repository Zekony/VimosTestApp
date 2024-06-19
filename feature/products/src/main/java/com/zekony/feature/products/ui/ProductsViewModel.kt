package com.zekony.feature.products.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zekony.domain.products.repositories.ApiProductsRepository
import com.zekony.domain.products.repositories.DbProductsRepository
import com.zekony.domain.products.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val apiProductsRepository: ApiProductsRepository,
    private val dbProductsRepository: DbProductsRepository
) : ViewModel() {

    init {
        initialize()
    }

    private val _state: MutableStateFlow<ProductsState> = MutableStateFlow(ProductsState())
    val state = _state.asStateFlow()

    private val _navigationSideEffects = MutableSharedFlow<ProductsSideEffect>()
    val navigationSideEffects = _navigationSideEffects.asSharedFlow()

    private fun initialize() {
        viewModelScope.launch {
            dbProductsRepository.getProducts().collect { productsList ->
                if (productsList.isEmpty()) makeServerRequest() else {
                    _state.value = state.value.copy(
                        downloadState = DownloadState.Success,
                        productsList = productsList
                    )
                }
            }
        }
    }

    private suspend fun makeServerRequest() {
        when (val response = apiProductsRepository.fetchProductsList()) {

            is Resource.Error -> {

                _state.value = state.value.copy(
                    downloadState = DownloadState.Error,
                    errorMessage = response.message
                )
            }

            is Resource.Success -> {
                _state.value = state.value.copy(
                    downloadState = DownloadState.Success,
                    productsList = response.data ?: emptyList()
                )
                response.data?.let { dbProductsRepository.addProducts(it) }
            }
        }
    }

    fun navigateToInfoScreen(gcode: Int) {
        viewModelScope.launch {
            _navigationSideEffects.emit(ProductsSideEffect.NavigateToInfoScreen(gcode))
        }
    }
}