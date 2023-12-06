package com.vinicius.githubexplorerapp.domain.usecase

import com.vinicius.githubexplorerapp.domain.model.UserFollower
import com.vinicius.githubexplorerapp.domain.repository.UserRepository
import javax.inject.Inject

class GetUserFollowersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(token: String, username: String): List<UserFollower> {
        return userRepository.getUserFollowers(token, username)
    }
}