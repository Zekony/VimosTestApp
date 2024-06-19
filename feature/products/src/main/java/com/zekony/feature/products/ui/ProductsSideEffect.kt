package com.zekony.feature.products.ui

sealed interface ProductsSideEffect {
    class NavigateToInfoScreen(val gcode: Int) : ProductsSideEffect
}
