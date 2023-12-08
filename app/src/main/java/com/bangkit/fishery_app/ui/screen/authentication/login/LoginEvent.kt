package com.bangkit.fishery_app.ui.screen.authentication.login

import android.content.Intent

sealed class LoginEvent {
    data class OnEmailChanged(val email: String): LoginEvent()
    data class OnPasswordChanged(val password: String): LoginEvent()
    data class LoginWithEmailPassword(val email: String, val password: String): LoginEvent()
    data class LoginWithGoogle(val intent: Intent): LoginEvent()
    data class SetLoadingState(val isLoading: Boolean): LoginEvent()
    object ResetState : LoginEvent()
}