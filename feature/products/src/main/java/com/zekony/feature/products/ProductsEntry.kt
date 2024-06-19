package com.zekony.feature.products

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zekony.feature.products.ui.ProductsScreen
import com.zekony.feature.products.ui.ProductsSideEffect
import com.zekony.feature.products.ui.ProductsViewModel

const val PRODUCTS_ROUTE = "productsEntry"
fun NavGraphBuilder.productsEntry(
    navigateToInfoScreen: (Int) -> Unit
) {
    composable(PRODUCTS_ROUTE) {
        val viewModel = hiltViewModel<ProductsViewModel>()

        ProductsScreen(viewModel)

        LaunchedEffect(Unit) {
            viewModel.navigationSideEffects.collect { sideEffect ->
                when (sideEffect) {
                    is ProductsSideEffect.NavigateToInfoScreen -> {
                        navigateToInfoScreen(sideEffect.gcode)
                    }
                }
            }
        }
    }
}