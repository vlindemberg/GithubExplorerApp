package com.vinicius.githubexplorerapp.domain.extensions

import com.vinicius.githubexplorerapp.data.model.UserResponse
import com.vinicius.githubexplorerapp.domain.model.User

fun UserResponse.toUser(): User = User(
    id = this.id,
    avatarUrl = this.avatarUrl,
    email = this.email,
    login = this.login,
    name = this.name,
)