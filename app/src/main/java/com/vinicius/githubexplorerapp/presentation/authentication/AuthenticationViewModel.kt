package com.vinicius.githubexplorerapp.presentation.authentication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinicius.githubexplorerapp.domain.usecase.GetAuthTokenUseCase
import com.vinicius.githubexplorerapp.domain.usecase.GetUserInfoUseCase
import com.vinicius.githubexplorerapp.util.UserLogged
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val getAuthTokenUseCase: GetAuthTokenUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    private val _authStates = MutableStateFlow(AuthenticationState(isLoading = true))
    val authStates: StateFlow<AuthenticationState> = _authStates

    private val _userStates = MutableStateFlow(AuthenticationState(isLoading = true))
    val userStates: StateFlow<AuthenticationState> = _userStates

    fun getAuthToken(code: String) {
        viewModelScope.launch {
            runCatching {
                getAuthTokenUseCase(code)
            }.onSuccess { token ->
                UserLogged.setAuthToken("Bearer " + token.token)
                _authStates.update {
                    AuthenticationState(
                        isLoading = false,
                        success = true,
                        token = UserLogged.getAuthToken(),
                        errorMessage = ""
                    )
                }
            }.onFailure { error ->
                Log.e("AUTH_ERROR", "Something went wrong:", error)
                _authStates.update {
                    AuthenticationState(
                        isLoading = false,
                        success = false,
                        token = it.token,
                        errorMessage = "Something went wrong: ${error.message}"
                    )
                }
            }
        }
    }

    fun getUserWithToken(token: String) {
        viewModelScope.launch {
            runCatching {
                getUserInfoUseCase(token)
            }.onSuccess { user ->
                UserLogged.setUserLogged(user)
                _userStates.update {
                    AuthenticationState(
                        isLoading = false,
                        success = true,
                        token = it.token,
                        errorMessage = ""
                    )
                }
            }.onFailure { error ->
                Log.e("USER_ERROR", "Something went wrong:", error)
                _userStates.update {
                    AuthenticationState(
                        isLoading = false,
                        success = false,
                        token = it.token,
                        errorMessage = "Something went wrong: ${error.message}"
                    )
                }
            }
        }
    }
}
