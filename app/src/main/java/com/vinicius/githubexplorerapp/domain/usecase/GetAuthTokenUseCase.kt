package com.vinicius.githubexplorerapp.domain.usecase

import com.vinicius.githubexplorerapp.BuildConfig
import com.vinicius.githubexplorerapp.domain.model.Token
import com.vinicius.githubexplorerapp.domain.repository.AuthenticationRepository
import javax.inject.Inject

class GetAuthTokenUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(code: String): Token {
        return authenticationRepository.getAuthToken(
            clientId = BuildConfig.CLIENT_ID,
            clientSecret = BuildConfig.CLIENT_SECRET,
            code = code,
            redirectUri = BuildConfig.REDIRECT_URI
        )
    }
}
