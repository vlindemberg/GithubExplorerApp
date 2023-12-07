package com.vinicius.githubexplorerapp.presentation.myRepositories

import com.vinicius.githubexplorerapp.domain.model.UserRepo

data class MyReposState(
    val isLoading: Boolean = true,
    val reposList: List<UserRepo> = emptyList(),
    val errorMessage: String = "",
)