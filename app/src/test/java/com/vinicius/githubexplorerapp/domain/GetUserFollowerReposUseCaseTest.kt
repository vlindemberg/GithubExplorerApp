package com.vinicius.githubexplorerapp.domain

import com.vinicius.githubexplorerapp.commons.BaseUnitTest
import com.vinicius.githubexplorerapp.commons.DefaultNetworkError
import com.vinicius.githubexplorerapp.domain.model.UserRepo
import com.vinicius.githubexplorerapp.domain.repository.UserRepository
import com.vinicius.githubexplorerapp.domain.usecase.GetUserFollowerReposUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetUserFollowerReposUseCaseTest : BaseUnitTest() {

    private val userRepository: UserRepository by lazy { mockk() }

    private val getUserFollowerReposUseCase by lazy {
        GetUserFollowerReposUseCase(userRepository)
    }

    private val token = "token"
    private val username = "username"

    @Test
    fun `Should GetUserFollowerReposUseCase return user followers repos`() = runBlocking {
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
        coEvery { userRepository.getFollowerUserRepos(token, username) } returns expectedResult
        val result = getUserFollowerReposUseCase(token, username)
        coVerify(exactly = 1) { userRepository.getFollowerUserRepos(token, username) }
        assertEquals(expectedResult, result)
    }

    @Test(expected = DefaultNetworkError::class)
    fun `Should GetUserFollowerReposUseCase return error`() {
        coEvery {
            userRepository.getFollowerUserRepos(
                token,
                username
            )
        } throws DefaultNetworkError(null)
        runBlocking { getUserFollowerReposUseCase(token, username) }
    }
}