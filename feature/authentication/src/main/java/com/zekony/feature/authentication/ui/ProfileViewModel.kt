package com.zekony.feature.authentication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zekony.domain.registration.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    init {
        initialize()
    }

    private val _state: MutableStateFlow<RegistrationState> = MutableStateFlow(RegistrationState())
    val state = _state.asStateFlow()

    private fun initialize() {
        viewModelScope.launch {
            val userName = authRepository.getUserName().first()
            _state.value = _state.value.copy(
                userName = userName,
                userNameDownloadState = if (userName.isEmpty()) DownloadState.NoData else DownloadState.Success
            )
        }
    }

    fun onNameInput(name: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                userName = if (name.length < 16) name.trimStart() else state.value.userName
            )
        }
    }

    fun saveUserName() {
        viewModelScope.launch {
            authRepository.saveUserName(state.value.userName)
            _state.value = _state.value.copy(
                userName = state.value.userName,
                userNameDownloadState = DownloadState.Success
            )
        }
    }

    fun openDialog(open: Boolean) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isUserNameDialogOpen = open
            )
        }
    }
}