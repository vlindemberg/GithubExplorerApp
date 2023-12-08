package com.vinicius.githubexplorerapp.domain.usecase

import com.vinicius.githubexplorerapp.domain.model.UserRepo
import com.vinicius.githubexplorerapp.domain.repository.UserRepository
import javax.inject.Inject

class CreateUserRepoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(token: String, repo: UserRepo): UserRepo {
        return userRepository.createUserRepo(token, repo)
    }
}