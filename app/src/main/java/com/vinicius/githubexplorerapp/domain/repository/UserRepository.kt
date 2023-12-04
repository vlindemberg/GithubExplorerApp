package com.vinicius.githubexplorerapp.domain.repository

import com.vinicius.githubexplorerapp.domain.model.User

interface UserRepository {
    suspend fun getUser(token: String) : User
}