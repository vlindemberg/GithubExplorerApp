package com.vinicius.githubexplorerapp.data.datasource

import com.vinicius.githubexplorerapp.data.model.AuthTokenResponse
import com.vinicius.githubexplorerapp.data.service.AuthenticationService
import javax.inject.Inject

class AuthenticationRemoteDataSourceImpl @Inject constructor(
    private val authenticationService: AuthenticationService,
) : AuthenticationRemoteDataSource {
    override suspend fun getAuthToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUri: String
    ): AuthTokenResponse {
        return authenticationService.getAuthToken(
            clientId = clientId,
            clientSecret = clientSecret,
            code = code,
            redirectUri = redirectUri
        )
    }
}
