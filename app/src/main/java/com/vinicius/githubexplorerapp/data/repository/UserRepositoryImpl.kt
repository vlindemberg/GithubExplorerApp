package com.vinicius.githubexplorerapp.data.repository

import com.vinicius.githubexplorerapp.data.datasource.UserRemoteDataSource
import com.vinicius.githubexplorerapp.domain.extensions.toUser
import com.vinicius.githubexplorerapp.domain.extensions.toUserFollowers
import com.vinicius.githubexplorerapp.domain.extensions.toUserRepos
import com.vinicius.githubexplorerapp.domain.model.User
import com.vinicius.githubexplorerapp.domain.model.UserFollower
import com.vinicius.githubexplorerapp.domain.model.UserRepo
import com.vinicius.githubexplorerapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    override suspend fun getUser(
        token: String
    ): User = userRemoteDataSource.getUser(token).toUser()

    override suspend fun getUserFollowers(
        token: String,
        username: String
    ): List<UserFollower> = userRemoteDataSource.getUserFollowers(token, username).toUserFollowers()

    override suspend fun getUserRepos(
        token: String,
        username: String
    ): List<UserRepo> = userRemoteDataSource.getUserRepos(token, username).toUserRepos()
}