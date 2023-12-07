package com.vinicius.githubexplorerapp.data.datasource

import com.vinicius.githubexplorerapp.data.model.UserFollowersResponse
import com.vinicius.githubexplorerapp.data.model.UserRepoResponse
import com.vinicius.githubexplorerapp.data.model.UserResponse

interface UserRemoteDataSource {
    suspend fun getUser(token: String): UserResponse
    suspend fun getUserFollowers(token: String, username: String): List<UserFollowersResponse>
    suspend fun getUserRepos(token: String, username: String): List<UserRepoResponse>
}