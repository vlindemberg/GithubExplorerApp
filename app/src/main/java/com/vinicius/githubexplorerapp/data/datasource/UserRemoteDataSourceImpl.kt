package com.vinicius.githubexplorerapp.data.datasource

import com.vinicius.githubexplorerapp.data.model.UserResponse
import com.vinicius.githubexplorerapp.data.service.UserService
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService
) : UserRemoteDataSource {
    override suspend fun getUser(token: String): UserResponse =
        userService.getUserInfo(token)
}