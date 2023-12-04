package com.vinicius.githubexplorerapp.data.datasource

import com.vinicius.githubexplorerapp.data.model.UserResponse

interface UserRemoteDataSource {
    suspend fun getUser(token: String): UserResponse
}