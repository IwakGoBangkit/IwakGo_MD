package com.bangkit.fishery_app.ui.screen.authentication.forget_password

data class ForgetPasswordState(
    val isLoading: Boolean = false,
    val resetPasswordSuccessful: Boolean = false,
    val resetPasswordError: String? = null,
    val email: String = ""
)