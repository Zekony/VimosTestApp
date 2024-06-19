package com.zekony.feature.products.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zekony.feature.products.ui.composables.ProductsList
import com.zekony.resources.R

@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel
) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when (state.downloadState) {
            DownloadState.Downloading -> {
                CircularProgressIndicator()
            }

            DownloadState.Success -> {
                ProductsList()
            }

            DownloadState.Error -> {
                Text(
                    text = state.errorMessage ?: stringResource(id = R.string.empty_error_message),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}


