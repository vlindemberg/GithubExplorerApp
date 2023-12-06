package com.vinicius.githubexplorerapp.presentation.followers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinicius.githubexplorerapp.domain.usecase.GetUserFollowersUseCase
import com.vinicius.githubexplorerapp.util.UserLogged
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(
    private val getUserFollowersUseCase: GetUserFollowersUseCase
) : ViewModel() {

    private val _userFollowersStates = MutableStateFlow(FollowersState(isLoading = true))
    val userFollowersStates: StateFlow<FollowersState> = _userFollowersStates

    init {
        getUserFollowers(UserLogged.getAuthToken(), UserLogged.getUserLogged().login)
    }

    private fun getUserFollowers(token: String, username: String) {
        viewModelScope.launch {
            runCatching {
                getUserFollowersUseCase(token, username)
            }.onSuccess { followersList ->
                _userFollowersStates.update {
                    FollowersState(
                        isLoading = false,
                        followersList = followersList,
                    )
                }
            }.onFailure { error ->
                _userFollowersStates.update {
                    FollowersState(
                        isLoading = false,
                        errorMessage = "Something went wrong: ${error.message}",
                    )
                }
            }
        }
    }
}
