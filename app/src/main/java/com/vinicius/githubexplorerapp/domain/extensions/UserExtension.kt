package com.vinicius.githubexplorerapp.domain.extensions

import com.vinicius.githubexplorerapp.data.model.UserFollowersResponse
import com.vinicius.githubexplorerapp.data.model.UserResponse
import com.vinicius.githubexplorerapp.domain.model.User
import com.vinicius.githubexplorerapp.domain.model.UserFollower

fun UserResponse.toUser(): User = User(
    id = this.id,
    avatarUrl = this.avatarUrl,
    email = this.email,
    login = this.login,
    name = this.name,
)

fun List<UserFollowersResponse>.toUserFollowers(): List<UserFollower> =
    this.map {
        UserFollower(
            id = it.id,
            login = it.login,
            avatarUrl = it.avatarUrl
        )
    }