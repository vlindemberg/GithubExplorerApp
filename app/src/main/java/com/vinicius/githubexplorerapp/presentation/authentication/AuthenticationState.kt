package com.vinicius.githubexplorerapp.presentation.authentication

data class AuthenticationState(
    val isLoading: Boolean = true,
    val success: Boolean = false,
    val errorMessage: String = "",
)