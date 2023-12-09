package com.vinicius.githubexplorerapp.presentation.profile

data class ProfileState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val errorMessage: String = "",
)
