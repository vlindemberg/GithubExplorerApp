package com.vinicius.githubexplorerapp.data.repository

import com.vinicius.githubexplorerapp.data.datasource.AuthenticationRemoteDataSource
import com.vinicius.githubexplorerapp.domain.extensions.toToken
import com.vinicius.githubexplorerapp.domain.model.Token
import com.vinicius.githubexplorerapp.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationRemoteDataSource: AuthenticationRemoteDataSource
) : AuthenticationRepository {
    override suspend fun getAuthToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUri: String
    ): Token {
        return authenticationRemoteDataSource.getAuthToken(
            clientId,
            clientSecret,
            code,
            redirectUri
        ).toToken()
    }
}