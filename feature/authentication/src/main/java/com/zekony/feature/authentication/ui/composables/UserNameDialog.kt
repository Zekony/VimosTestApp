package com.zekony.feature.authentication.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.zekony.feature.authentication.R


@Composable
fun UserNameDialog(
    name: String,
    onNameInput: (String) -> Unit,
    saveName: () -> Unit
) {
    Dialog(onDismissRequest = { }) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
        ) {
            Text(stringResource(R.string.enterName), style = MaterialTheme.typography.labelSmall)
            TextField(
                value = name,
                onValueChange = { onNameInput(it) }
            )
            Button(
                onClick = {
                    saveName()
                },
                enabled = name.length > 3
            ) {
                Text(stringResource(R.string.save), style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}