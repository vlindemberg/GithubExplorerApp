package com.vinicius.githubexplorerapp.domain.extensions

import com.vinicius.githubexplorerapp.data.model.UserFollowersResponse
import com.vinicius.githubexplorerapp.data.model.UserRepoResponse
import com.vinicius.githubexplorerapp.data.model.UserResponse
import com.vinicius.githubexplorerapp.domain.model.User
import com.vinicius.githubexplorerapp.domain.model.UserFollower
import com.vinicius.githubexplorerapp.domain.model.UserRepo

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

fun List<UserRepoResponse>.toUserRepos(): List<UserRepo> =
    this.map {
        UserRepo(
            id = it.id,
            name = it.name,
            description = it.description.orEmpty()
        )
    }