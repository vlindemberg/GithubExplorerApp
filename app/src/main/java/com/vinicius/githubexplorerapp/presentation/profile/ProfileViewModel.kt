package com.vinicius.githubexplorerapp.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinicius.githubexplorerapp.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _logoutStates = MutableStateFlow(ProfileState(isLoading = false))
    val logoutStates: StateFlow<ProfileState> = _logoutStates

    fun logout(tokenBearer: String, token: String) {
        viewModelScope.launch {
            runCatching {
                logoutUseCase.invoke(tokenBearer, token)
            }.onSuccess {
                _logoutStates.update {
                    ProfileState(
                        isLoading = false,
                        success = true,
                    )
                }
            }.onFailure { error ->
                _logoutStates.update {
                    ProfileState(
                        isLoading = false,
                        success = false,
                        errorMessage = "Something went wrong: ${error.message}",
                    )
                }
            }
        }
    }
}