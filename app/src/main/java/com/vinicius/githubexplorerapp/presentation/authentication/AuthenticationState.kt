package com.vinicius.githubexplorerapp.presentation.authentication

data class AuthenticationState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val token: String = "",
    val errorMessage: String = "",
)