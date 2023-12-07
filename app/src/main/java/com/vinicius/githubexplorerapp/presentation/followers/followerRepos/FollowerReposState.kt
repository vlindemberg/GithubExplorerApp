package com.vinicius.githubexplorerapp.presentation.followers.followerRepos

import com.vinicius.githubexplorerapp.domain.model.UserRepo

data class FollowerReposState(
    val isLoading: Boolean = true,
    val followerReposList: List<UserRepo> = emptyList(),
    val errorMessage: String = "",
)