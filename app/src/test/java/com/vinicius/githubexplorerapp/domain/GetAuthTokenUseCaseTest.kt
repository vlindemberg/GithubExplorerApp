package com.vinicius.githubexplorerapp.domain

import com.vinicius.githubexplorerapp.BuildConfig
import com.vinicius.githubexplorerapp.commons.BaseUnitTest
import com.vinicius.githubexplorerapp.commons.DefaultNetworkError
import com.vinicius.githubexplorerapp.domain.model.Token
import com.vinicius.githubexplorerapp.domain.repository.AuthenticationRepository
import com.vinicius.githubexplorerapp.domain.usecase.GetAuthTokenUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetAuthTokenUseCaseTest : BaseUnitTest() {

    private val authenticationRepository: AuthenticationRepository by lazy { mockk() }

    private val getAuthTokenUseCase by lazy {
        GetAuthTokenUseCase(authenticationRepository)
    }

    private val code = "code"

    @Test
    fun `Should GetAuthTokenUseCase return token`() = runBlocking {
        val expectedResult = Token(token = "token", type = "type")
        coEvery {
            authenticationRepository.getAuthToken(
                clientId = BuildConfig.CLIENT_ID,
                clientSecret = BuildConfig.CLIENT_SECRET,
                code = code,
                redirectUri = BuildConfig.REDIRECT_URI
            )
        } returns expectedResult
        val result = getAuthTokenUseCase(code)
        coVerify(exactly = 1) {
            authenticationRepository.getAuthToken(
                clientId = BuildConfig.CLIENT_ID,
                clientSecret = BuildConfig.CLIENT_SECRET,
                code = code,
                redirectUri = BuildConfig.REDIRECT_URI
            )
        }
        assertEquals(expectedResult, result)
    }

    @Test(expected = DefaultNetworkError::class)
    fun `Should GetUserInfoUseCase return error`() {
        coEvery {
            authenticationRepository.getAuthToken(
                clientId = BuildConfig.CLIENT_ID,
                clientSecret = BuildConfig.CLIENT_SECRET,
                code = code,
                redirectUri = BuildConfig.REDIRECT_URI
            )
        } throws DefaultNetworkError(null)
        runBlocking { getAuthTokenUseCase(code) }
    }
}