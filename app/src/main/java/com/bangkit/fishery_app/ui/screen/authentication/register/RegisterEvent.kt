package com.bangkit.fishery_app.ui.screen.authentication.register

sealed class RegisterEvent {

    data class RegisterWithEmailPassword(
        val name: String,
        val email: String,
        val password: String
    ) : RegisterEvent()

    data class OnNameChanged(val name: String) : RegisterEvent()
    data class OnEmailChanged(val email: String) : RegisterEvent()
    data class OnPasswordChanged(val password: String) : RegisterEvent()
    data class OnPasswordConfirmationChanged(val confirmationPassword: String) : RegisterEvent()
    data class SetLoadingState(val isLoading: Boolean) : RegisterEvent()
    object ResetState : RegisterEvent()
}