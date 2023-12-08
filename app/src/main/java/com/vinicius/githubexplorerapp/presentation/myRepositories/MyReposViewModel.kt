package com.vinicius.githubexplorerapp.presentation.myRepositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinicius.githubexplorerapp.domain.model.UserRepo
import com.vinicius.githubexplorerapp.domain.usecase.CreateUserRepoUseCase
import com.vinicius.githubexplorerapp.domain.usecase.GetUserReposUseCase
import com.vinicius.githubexplorerapp.util.UserLogged
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyReposViewModel @Inject constructor(
    private val getUserReposUseCase: GetUserReposUseCase,
    private val createUserRepoUseCase: CreateUserRepoUseCase
) : ViewModel() {

    private val _userReposStates = MutableStateFlow(MyReposState(isLoading = true))
    val userReposStates: StateFlow<MyReposState> = _userReposStates

    init {
        getUserRepos(UserLogged.getAuthToken())
    }

    private fun getUserRepos(token: String) {
        viewModelScope.launch {
            runCatching {
                getUserReposUseCase(token)
            }.onSuccess { userRepos ->
                _userReposStates.update {
                    MyReposState(
                        isLoading = false,
                        reposList = userRepos
                    )
                }
            }.onFailure { error ->
                _userReposStates.update {
                    MyReposState(
                        isLoading = false,
                        errorMessage = "Something went wrong: ${error.message}",
                    )
                }
            }
        }
    }

    fun createRepo(token: String, repo: UserRepo) {
        viewModelScope.launch {
            runCatching {
                createUserRepoUseCase(token, repo)
            }.onSuccess { userRepo ->
                _userReposStates.update {
                    val repos = it.reposList as ArrayList<UserRepo>
                    repos.add(userRepo)
                    MyReposState(
                        isLoading = false,
                        reposList = repos
                    )
                }
            }.onFailure { error ->
                _userReposStates.update {
                    MyReposState(
                        isLoading = false,
                        reposList = it.reposList,
                        errorMessage = "Something went wrong: ${error.message}",
                    )
                }
            }
        }
    }
}
