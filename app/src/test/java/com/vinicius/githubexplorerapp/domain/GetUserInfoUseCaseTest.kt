package com.vinicius.githubexplorerapp.domain

import com.vinicius.githubexplorerapp.commons.BaseUnitTest
import com.vinicius.githubexplorerapp.commons.DefaultNetworkError
import com.vinicius.githubexplorerapp.domain.model.User
import com.vinicius.githubexplorerapp.domain.repository.UserRepository
import com.vinicius.githubexplorerapp.domain.usecase.GetUserInfoUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetUserInfoUseCaseTest : BaseUnitTest() {

    private val userRepository: UserRepository by lazy { mockk() }

    private val getUserInfoUseCase by lazy {
        GetUserInfoUseCase(userRepository)
    }

    private val token = "token"

    @Test
    fun `Should GetUserInfoUseCase return User Info`() = runBlocking {
        val expectedResult = User(
            id = 1,
            avatarUrl = "1",
            email = "1",
            login = "1",
            name = "1"
        )
        coEvery { userRepository.getUser(token) } returns expectedResult
        val result = getUserInfoUseCase(token)
        coVerify(exactly = 1) { userRepository.getUser(token) }
        assertEquals(expectedResult, result)
    }

    @Test(expected = DefaultNetworkError::class)
    fun `Should GetUserInfoUseCase return error`() {
        coEvery { userRepository.getUser(token) } throws DefaultNetworkError(null)
        runBlocking { getUserInfoUseCase(token) }
    }
}


