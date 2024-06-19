package com.zekony.feature.productinfo.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zekony.feature.productinfo.ui.composables.ItemInfoScreen


@Composable
fun ProductInfoScreen(viewModel: ProductInfoViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        if (state.productItem == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            state.productItem?.let { certainlyNotTullItem ->
                ItemInfoScreen(
                    certainlyNotTullItem
                )
            }
        }
    }
}