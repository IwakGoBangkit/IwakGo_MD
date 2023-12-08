package com.bangkit.fishery_app.ui.screen.authentication.login

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.fishery_app.data.repository.AuthRepository
import com.bangkit.fishery_app.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.LoginWithEmailPassword -> {
                loginWithEmailPassword(event.email, event.password)
            }
            is LoginEvent.LoginWithGoogle -> {
                loginWithIntent(event.intent)
            }
            is LoginEvent.OnEmailChanged -> {
                _state.update {
                    it.copy(
                        email = event.email,
                    )
                }
            }
            is LoginEvent.OnPasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.password
                    )
                }
            }
            LoginEvent.ResetState -> {
                _state.update {
                    LoginState()
                }
            }
            is LoginEvent.SetLoadingState -> {
                _state.update {
                    it.copy(
                        isLoading = event.isLoading
                    )
                }
            }
        }
    }

    private fun loginWithEmailPassword(email: String, password: String) = viewModelScope.launch {
        authRepository.loginWithEmail(email, password).collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            loginError = result.message
                        )
                    }
                }

                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }

                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            loginSuccessful = true,
                            loginResult = result.data
                        )
                    }
                }
            }
        }
    }

    private fun loginWithIntent(intent: Intent) = viewModelScope.launch {
        authRepository.loginWithIntent(intent).collect{result ->
            when(result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            loginError = result.message
                        )
                    }
                }
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            loginSuccessful = true,
                            loginResult = result.data
                        )
                    }
                }
            }
        }
    }
}