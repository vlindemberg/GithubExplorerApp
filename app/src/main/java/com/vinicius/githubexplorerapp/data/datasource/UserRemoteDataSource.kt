package com.vinicius.githubexplorerapp.data.datasource

import com.vinicius.githubexplorerapp.data.model.UserFollowersResponse
import com.vinicius.githubexplorerapp.data.model.UserRepoResponse
import com.vinicius.githubexplorerapp.data.model.UserResponse
import com.vinicius.githubexplorerapp.domain.model.UserRepo

interface UserRemoteDataSource {
    suspend fun getUser(token: String): UserResponse
    suspend fun getUserRepos(token: String): List<UserRepoResponse>
    suspend fun createUserRepo(
        token: String,
        repo: UserRepo,
    ): UserRepoResponse

    suspend fun getUserFollowers(token: String, username: String): List<UserFollowersResponse>
    suspend fun getFollowerUserRepos(token: String, username: String): List<UserRepoResponse>
}