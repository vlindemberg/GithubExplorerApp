package com.vinicius.githubexplorerapp.presentation.followers

import com.vinicius.githubexplorerapp.domain.model.UserFollower

data class FollowersState(
    val isLoading: Boolean = true,
    val followersList: List<UserFollower> = emptyList(),
    val errorMessage: String = "",
)