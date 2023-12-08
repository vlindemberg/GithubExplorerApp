package com.vinicius.githubexplorerapp.domain.repository

import com.vinicius.githubexplorerapp.domain.model.User
import com.vinicius.githubexplorerapp.domain.model.UserFollower
import com.vinicius.githubexplorerapp.domain.model.UserRepo

interface UserRepository {
    suspend fun getUser(token: String): User
    suspend fun getUserRepos(token: String): List<UserRepo>
    suspend fun createUserRepo(
        token: String,
        repo: UserRepo,
    ): UserRepo
    suspend fun getUserFollowers(token: String, username: String): List<UserFollower>
    suspend fun getFollowerUserRepos(token: String, username: String): List<UserRepo>
}