package com.zekony.feature.authentication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.zekony.feature.authentication.R
import com.zekony.feature.authentication.ui.composables.UserNameDialog

@Composable
fun RegistrationScreen(
    viewModel: ProfileViewModel
) {
    val state by viewModel.state.collectAsState()

    if (state.isUserNameDialogOpen) {
        UserNameDialog(
            name = state.userName,
            onNameInput = viewModel::onNameInput,
            saveName = {
                viewModel.saveUserName()
                viewModel.openDialog(false)
            }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (state.userNameDownloadState) {
            DownloadState.Downloading -> {
                CircularProgressIndicator()
            }

            DownloadState.Success -> {
                Text(stringResource(R.string.hello), style = MaterialTheme.typography.displayMedium)
                Text(state.userName + '!', style = MaterialTheme.typography.displayLarge)
            }

            DownloadState.NoData -> viewModel.openDialog(true)
        }
    }
}

