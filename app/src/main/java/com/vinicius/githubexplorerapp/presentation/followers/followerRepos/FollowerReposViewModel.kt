package com.vinicius.githubexplorerapp.presentation.followers.followerRepos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinicius.githubexplorerapp.domain.usecase.GetUserFollowerReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowerReposViewModel @Inject constructor(
    private val getUserFollowerReposUseCase: GetUserFollowerReposUseCase
) : ViewModel() {

    private val _userFollowerReposStates = MutableStateFlow(FollowerReposState(isLoading = true))
    val userFollowerReposStates: StateFlow<FollowerReposState> = _userFollowerReposStates

    fun getUserFollowerRepos(token: String, username: String) {
        viewModelScope.launch {
            runCatching {
                getUserFollowerReposUseCase(token, username)
            }.onSuccess { followerReposList ->
                _userFollowerReposStates.update {
                    FollowerReposState(
                        isLoading = false,
                        followerReposList = followerReposList,
                    )
                }
            }.onFailure { error ->
                _userFollowerReposStates.update {
                    FollowerReposState(
                        isLoading = false,
                        errorMessage = "Something went wrong: ${error.message}",
                    )
                }
            }
        }
    }
}
