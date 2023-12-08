package com.bangkit.fishery_app.ui.screen.authentication.forget_password

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
class ForgetPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ForgetPasswordState())
    val state = _state.asStateFlow()

    fun onEvent(event: ForgetPasswordEvent) {
        when (event) {
            is ForgetPasswordEvent.ForgetPasswordWithEmail -> {
                resetPassword(event.email)
            }

            is ForgetPasswordEvent.OnEmailChanged -> {
                _state.update {
                    it.copy(
                        email = event.email
                    )
                }
            }

            ForgetPasswordEvent.ResetState -> {
                _state.update {
                    ForgetPasswordState()
                }
            }

            is ForgetPasswordEvent.SetLoadingState -> {
                _state.update {
                    it.copy(
                        isLoading = event.isLoading
                    )
                }
            }
        }
    }

    private fun resetPassword(email: String) = viewModelScope.launch {
        authRepository.resetPassword(email).collect { result ->
            when(result) {
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            resetPasswordSuccessful = true
                        )
                    }
                }
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            resetPasswordError = result.message
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
            }
        }
    }

}