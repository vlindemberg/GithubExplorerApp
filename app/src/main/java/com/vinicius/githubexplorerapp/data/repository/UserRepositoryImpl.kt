package com.vinicius.githubexplorerapp.data.repository

import com.vinicius.githubexplorerapp.data.datasource.UserRemoteDataSource
import com.vinicius.githubexplorerapp.domain.extensions.toUser
import com.vinicius.githubexplorerapp.domain.extensions.toUserFollowers
import com.vinicius.githubexplorerapp.domain.extensions.toUserRepo
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

    override suspend fun getUserRepos(
        token: String
    ): List<UserRepo> = userRemoteDataSource.getUserRepos(token).toUserRepos()

    override suspend fun createUserRepo(
        token: String,
        repo: UserRepo,
    ): UserRepo =
        userRemoteDataSource.createUserRepo(token, repo).toUserRepo()

    override suspend fun getUserFollowers(
        token: String,
        username: String
    ): List<UserFollower> = userRemoteDataSource.getUserFollowers(token, username).toUserFollowers()

    override suspend fun getFollowerUserRepos(
        token: String,
        username: String
    ): List<UserRepo> = userRemoteDataSource.getFollowerUserRepos(token, username).toUserRepos()
}