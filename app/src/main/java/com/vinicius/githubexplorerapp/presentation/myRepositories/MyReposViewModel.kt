package com.vinicius.githubexplorerapp.presentation.myRepositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val getUserReposUseCase: GetUserReposUseCase
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
}
