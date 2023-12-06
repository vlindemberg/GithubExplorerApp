package com.vinicius.githubexplorerapp.domain.repository

import com.vinicius.githubexplorerapp.domain.model.UserFollower
import com.vinicius.githubexplorerapp.domain.model.User

interface UserRepository {
    suspend fun getUser(token: String) : User
    suspend fun getUserFollowers(token: String, username: String): List<UserFollower>
}