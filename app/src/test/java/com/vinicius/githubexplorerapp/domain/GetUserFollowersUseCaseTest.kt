package com.vinicius.githubexplorerapp.domain

import com.vinicius.githubexplorerapp.commons.BaseUnitTest
import com.vinicius.githubexplorerapp.commons.DefaultNetworkError
import com.vinicius.githubexplorerapp.domain.model.UserFollower
import com.vinicius.githubexplorerapp.domain.repository.UserRepository
import com.vinicius.githubexplorerapp.domain.usecase.GetUserFollowersUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetUserFollowersUseCaseTest : BaseUnitTest() {

    private val userRepository: UserRepository by lazy { mockk() }

    private val getUserFollowersUseCase by lazy {
        GetUserFollowersUseCase(userRepository)
    }

    private val token = "token"
    private val username = "username"

    @Test
    fun `Should GetUserFollowersUseCase return user followers`() = runBlocking {
        val expectedResult = listOf(
            UserFollower(
                id = 1,
                login = "1",
                avatarUrl = "1",
            ),
            UserFollower(
                id = 2,
                login = "2",
                avatarUrl = "2",
            )
        )
        coEvery { userRepository.getUserFollowers(token, username) } returns expectedResult
        val result = getUserFollowersUseCase(token, username)
        coVerify(exactly = 1) { userRepository.getUserFollowers(token, username) }
        assertEquals(expectedResult, result)
    }

    @Test(expected = DefaultNetworkError::class)
    fun `Should GetUserFollowersUseCase return error`() {
        coEvery {
            userRepository.getUserFollowers(
                token,
                username
            )
        } throws DefaultNetworkError(null)
        runBlocking { getUserFollowersUseCase(token, username) }
    }
}