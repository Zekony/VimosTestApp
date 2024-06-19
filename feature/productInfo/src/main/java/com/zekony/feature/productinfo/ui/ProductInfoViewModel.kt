package com.zekony.feature.productinfo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zekony.domain.products.repositories.DbProductsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@HiltViewModel(assistedFactory = ProductInfoViewModel.ProductInfoModelFactory::class)
class ProductInfoViewModel @AssistedInject constructor(
    @Assisted private val gcode: Int,
    private val dbProductsRepository: DbProductsRepository,
) : ViewModel() {

    @AssistedFactory
    interface ProductInfoModelFactory {
        fun create(gcode: Int): ProductInfoViewModel
    }

    init {
        initialize()
    }

    private val _state: MutableStateFlow<ProductInfoState> = MutableStateFlow(ProductInfoState())
    val state = _state.asStateFlow()

    private fun initialize() {
        viewModelScope.launch {
            dbProductsRepository.getProductByGcode(gcode).collect {
                _state.value = state.value.copy(
                    productItem = it
                )
            }
        }
    }
}