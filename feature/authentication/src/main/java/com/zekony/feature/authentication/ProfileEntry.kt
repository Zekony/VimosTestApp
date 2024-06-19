package com.zekony.feature.authentication

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zekony.feature.authentication.ui.ProfileViewModel
import com.zekony.feature.authentication.ui.RegistrationScreen


const val REGISTRATION_ROUTE = "registrationEntry"
fun NavGraphBuilder.registrationEntry(

) {
    composable(REGISTRATION_ROUTE) {
        val viewModel = hiltViewModel<ProfileViewModel>()

        RegistrationScreen(viewModel)
    }
}