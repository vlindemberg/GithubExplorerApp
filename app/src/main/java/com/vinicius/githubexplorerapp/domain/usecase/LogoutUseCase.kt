package com.vinicius.githubexplorerapp.domain.usecase

import com.vinicius.githubexplorerapp.BuildConfig
import com.vinicius.githubexplorerapp.domain.repository.AuthenticationRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(tokenBearer: String, token: String): Any {
        return authenticationRepository.deleteAuthToken(
            tokenBearer = tokenBearer,
            clientId = BuildConfig.CLIENT_ID,
            token = token
        )
    }
}