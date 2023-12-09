package com.vinicius.githubexplorerapp.domain.repository

import com.vinicius.githubexplorerapp.domain.model.Token

interface AuthenticationRepository {
    suspend fun getAuthToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUri: String
    ): Token

    suspend fun deleteAuthToken(
        tokenBearer: String,
        clientId: String,
        token: String,
    ): Any
}