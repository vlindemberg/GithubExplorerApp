package com.vinicius.githubexplorerapp.presentation.authentication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinicius.githubexplorerapp.domain.usecase.GetAuthTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val getAuthTokenUseCase: GetAuthTokenUseCase
) : ViewModel() {

    private val _states = MutableStateFlow(AuthenticationState(isLoading = true))
    val states: StateFlow<AuthenticationState> = _states

    fun getAuthToken(code: String) {
        viewModelScope.launch {
            runCatching {
                getAuthTokenUseCase(code)
            }.onSuccess {
                _states.update {
                    AuthenticationState(
                        isLoading = false,
                        success = true,
                        errorMessage = ""
                    )
                }
            }.onFailure { error ->
                Log.e("AUTH_ERROR", "Something went wrong:", error)
                _states.update {
                    AuthenticationState(
                        isLoading = false,
                        success = false,
                        errorMessage = "Something went wrong: ${error.message}"
                    )
                }
            }
        }
    }
}
