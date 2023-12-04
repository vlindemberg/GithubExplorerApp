package com.vinicius.githubexplorerapp.data.repository

import com.vinicius.githubexplorerapp.data.datasource.UserRemoteDataSource
import com.vinicius.githubexplorerapp.domain.extensions.toUser
import com.vinicius.githubexplorerapp.domain.model.User
import com.vinicius.githubexplorerapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    override suspend fun getUser(token: String): User = userRemoteDataSource.getUser(token).toUser()
}