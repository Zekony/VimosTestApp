package com.zekony.feature.productinfo

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zekony.feature.productinfo.ui.ProductInfoScreen
import com.zekony.feature.productinfo.ui.ProductInfoViewModel

const val PRODUCT_INFO_ENTRY = "PRODUCT_INFO_ENTRY"
fun NavGraphBuilder.productInfoEntry() {
    composable(
        route = "${PRODUCT_INFO_ENTRY}/{gcode}",
        arguments = listOf(
            navArgument("gcode") {
                type = NavType.IntType
            }
        )
    ) { entry ->
        val id = entry.arguments?.getInt("gcode") ?: 0
        val viewModel =
            hiltViewModel<ProductInfoViewModel, ProductInfoViewModel.ProductInfoModelFactory> { factory ->
                factory.create(id)
            }
        ProductInfoScreen(viewModel)
    }
}

