package com.vinicius.githubexplorerapp.data.datasource

import com.vinicius.githubexplorerapp.data.model.UserFollowersResponse
import com.vinicius.githubexplorerapp.data.model.UserRepoResponse
import com.vinicius.githubexplorerapp.data.model.UserResponse
import com.vinicius.githubexplorerapp.data.service.UserService
import com.vinicius.githubexplorerapp.domain.extensions.toUserRepoRequest
import com.vinicius.githubexplorerapp.domain.model.UserRepo
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService
) : UserRemoteDataSource {
    override suspend fun getUser(
        token: String
    ): UserResponse = userService.getUserInfo(token)

    override suspend fun getUserRepos(
        token: String
    ): List<UserRepoResponse> = userService.getUserRepos(token)

    override suspend fun createUserRepo(
        token: String,
        repo: UserRepo
    ): UserRepoResponse =
        userService.createUserRepo(token, repo.toUserRepoRequest())

    override suspend fun getUserFollowers(
        token: String,
        username: String
    ): List<UserFollowersResponse> = userService.getUserFollowers(token, username)

    override suspend fun getFollowerUserRepos(
        token: String,
        username: String
    ): List<UserRepoResponse> = userService.getFollowerUserRepos(token, username)
}