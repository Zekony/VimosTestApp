package com.zekony.feature.authentication.ui

data class RegistrationState(
    val userName: String = "",
    val isUserNameDialogOpen: Boolean = false,
    val userNameDownloadState: DownloadState = DownloadState.Downloading
)

enum class DownloadState{
    Downloading, Success, NoData
}