package com.vinicius.githubexplorerapp.viewmodel

import app.cash.turbine.test
import com.vinicius.githubexplorerapp.commons.BaseUnitTest
import com.vinicius.githubexplorerapp.domain.model.Token
import com.vinicius.githubexplorerapp.domain.model.User
import com.vinicius.githubexplorerapp.domain.usecase.GetAuthTokenUseCase
import com.vinicius.githubexplorerapp.domain.usecase.GetUserInfoUseCase
import com.vinicius.githubexplorerapp.presentation.authentication.AuthenticationState
import com.vinicius.githubexplorerapp.presentation.authentication.AuthenticationViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AuthenticationViewModelTest : BaseUnitTest() {

    @Mock
    lateinit var getAuthTokenUseCase: GetAuthTokenUseCase

    @Mock
    lateinit var getUserInfoUseCase: GetUserInfoUseCase

    @Mock
    lateinit var authenticationViewModel: AuthenticationViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        authenticationViewModel = AuthenticationViewModel(getAuthTokenUseCase, getUserInfoUseCase)
    }

    private val code = "code"
    private val token = Token("token", "type")

    @Test
    fun `SHOULD Succeed WHEN catching token`() = runTest {
        Mockito.`when`(getAuthTokenUseCase(code)).thenReturn(token)

        authenticationViewModel.getAuthToken(code)

        authenticationViewModel.authStates.test {
            assertEquals(
                AuthenticationState(
                    isLoading = false,
                    success = true,
                    token = token.type + " " + token.token,
                    errorMessage = ""
                ), awaitItem()
            )
        }
    }

    @Test
    fun `SHOULD Fail WHEN catching token`() = runTest {
        val errorMessage = "error message"
        val exception = RuntimeException(errorMessage)
        val expectedResult = AuthenticationState(
            isLoading = false,
            success = false,
            token = "",
            errorMessage = "Something went wrong: ${exception.message}"
        )
        Mockito.`when`(getAuthTokenUseCase(code)).thenThrow(exception)

        authenticationViewModel.getAuthToken(code)

        authenticationViewModel.authStates.test {
            assertEquals(AuthenticationState(), awaitItem())
            assertEquals(expectedResult, awaitItem())
        }
    }


    @Test
    fun `SHOULD Succeed WHEN catching user with token`() = runTest {
        Mockito.`when`(getUserInfoUseCase(token.token)).thenReturn(User())

        authenticationViewModel.getUserWithToken(token.token)

        authenticationViewModel.userStates.test {
            assertEquals(
                AuthenticationState(
                    isLoading = false,
                    success = true,
                    token = "",
                    errorMessage = ""
                ), awaitItem()
            )
        }
    }
}