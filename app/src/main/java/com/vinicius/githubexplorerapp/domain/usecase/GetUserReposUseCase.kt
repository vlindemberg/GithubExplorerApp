package com.vinicius.githubexplorerapp.domain.usecase

import com.vinicius.githubexplorerapp.domain.model.UserRepo
import com.vinicius.githubexplorerapp.domain.repository.UserRepository
import javax.inject.Inject

class GetUserReposUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(token: String): List<UserRepo> {
        return userRepository.getUserRepos(token)
    }
}