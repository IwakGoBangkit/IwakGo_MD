package com.bangkit.fishery_app.ui.screen.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.fishery_app.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var isLoggedIn = mutableStateOf(true)
        private set

    init {
        viewModelScope.launch {
            authRepository.getLoggedUser().collect { user ->
                isLoggedIn.value = user != null
            }
        }
    }
}