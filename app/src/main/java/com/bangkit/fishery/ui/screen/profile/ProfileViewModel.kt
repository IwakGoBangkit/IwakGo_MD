package com.bangkit.fishery.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.fishery.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    init {
        getUserProfile()
    }

    fun onEvent(event: ProfileEvent) {
        when(event) {
            ProfileEvent.OnLogout -> {
                logOut()
            }
            else -> {}
        }
    }

    private fun getUserProfile() = viewModelScope.launch {
        authRepository.getLoggedUser().collect {userData ->
            _state.update {
                it.copy(
                    userData = userData
                )
            }
        }
    }

    private fun logOut() = viewModelScope.launch {
        authRepository.signOut()
        _state.update {
            it.copy(
                isLoggedOut = true
            )
        }
    }
}