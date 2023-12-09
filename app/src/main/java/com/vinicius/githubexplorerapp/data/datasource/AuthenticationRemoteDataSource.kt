package com.vinicius.githubexplorerapp.data.datasource

import com.vinicius.githubexplorerapp.data.model.AuthTokenResponse

interface AuthenticationRemoteDataSource {
    suspend fun getAuthToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUri: String
    ): AuthTokenResponse

    suspend fun deleteAuthToken(
        tokenBearer: String,
        clientId: String,
        token: String
    ): Any
}