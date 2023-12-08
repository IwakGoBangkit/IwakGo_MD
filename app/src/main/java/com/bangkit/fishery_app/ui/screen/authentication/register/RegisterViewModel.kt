package com.bangkit.fishery_app.ui.screen.authentication.register

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
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.OnEmailChanged -> {
                _state.update {
                    it.copy(
                        email = event.email
                    )
                }
            }
            is RegisterEvent.OnNameChanged -> {
                _state.update {
                    it.copy(
                        name = event.name
                    )
                }
            }
            is RegisterEvent.OnPasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.password
                    )
                }
            }
            is RegisterEvent.OnPasswordConfirmationChanged -> {
                _state.update {
                    it.copy(
                        confirmationPassword = event.confirmationPassword
                    )
                }
            }
            is RegisterEvent.RegisterWithEmailPassword -> {
                registerWithEmailPassword(event.name, event.email, event.password)
            }
            RegisterEvent.ResetState -> {
                _state.update {
                    RegisterState()
                }
            }
            is RegisterEvent.SetLoadingState -> {
                _state.update {
                    it.copy(
                        isLoading = event.isLoading
                    )
                }
            }
        }
    }

    private fun registerWithEmailPassword(name: String, email: String, password: String) = viewModelScope.launch {
        authRepository.registerWithEmail(name, email, password).collect {result ->
            when(result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            registerError = result.message,
                            isLoading = false
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
                            registerSuccessful = true,
                            registerResult = result.data,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}