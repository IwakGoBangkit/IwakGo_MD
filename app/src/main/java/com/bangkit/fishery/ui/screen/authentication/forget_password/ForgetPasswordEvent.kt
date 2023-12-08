package com.bangkit.fishery.ui.screen.authentication.forget_password

sealed class ForgetPasswordEvent {
    data class OnEmailChanged(val email: String): ForgetPasswordEvent()
    data class ForgetPasswordWithEmail(val email: String): ForgetPasswordEvent()
    data class SetLoadingState(val isLoading: Boolean): ForgetPasswordEvent()
    object ResetState : ForgetPasswordEvent()
}