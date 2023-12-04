package com.vinicius.githubexplorerapp.domain.usecase

import com.vinicius.githubexplorerapp.domain.model.User
import com.vinicius.githubexplorerapp.domain.repository.UserRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(token: String): User {
        return userRepository.getUser(token)
    }
}
