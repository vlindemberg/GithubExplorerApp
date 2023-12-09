package com.vinicius.githubexplorerapp.domain

import com.vinicius.githubexplorerapp.commons.BaseUnitTest
import com.vinicius.githubexplorerapp.commons.DefaultNetworkError
import com.vinicius.githubexplorerapp.domain.model.UserRepo
import com.vinicius.githubexplorerapp.domain.repository.UserRepository
import com.vinicius.githubexplorerapp.domain.usecase.GetUserReposUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetUserReposUseCaseTest : BaseUnitTest() {

    private val userRepository: UserRepository by lazy { mockk() }

    private val getUserReposUseCase by lazy {
        GetUserReposUseCase(userRepository)
    }

    private val token = "token"

    @Test
    fun `Should GetUserReposUseCase return user repos`() = runBlocking {
        val expectedResult = listOf(
            UserRepo(
                name = "1",
                description = "1",
                homepage = "1",
                private = false,
                isTemplate = true,
            ),
            UserRepo(
                name = "2",
                description = "2",
                homepage = "2",
                private = false,
                isTemplate = true,
            )
        )
        coEvery { userRepository.getUserRepos(token) } returns expectedResult
        val result = getUserReposUseCase(token)
        coVerify(exactly = 1) { userRepository.getUserRepos(token) }
        TestCase.assertEquals(expectedResult, result)
    }

    @Test(expected = DefaultNetworkError::class)
    fun `Should GetUserFollowersUseCase return error`() {
        coEvery {
            userRepository.getUserRepos(token)
        } throws DefaultNetworkError(null)
        runBlocking { getUserReposUseCase(token) }
    }
}