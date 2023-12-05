package com.bangkit.fishery.ui.screen.authentication.register

import com.bangkit.fishery.ui.screen.authentication.model.UserModel

data class RegisterState(
    val registerSuccessful: Boolean = false,
    val registerError: String? = null,
    val registerResult: UserModel? = null,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmationPassword: String = "",
    val isLoading: Boolean = false
)