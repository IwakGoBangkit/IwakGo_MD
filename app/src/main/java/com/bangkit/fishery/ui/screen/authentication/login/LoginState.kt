package com.bangkit.fishery.ui.screen.authentication.login

import com.bangkit.fishery.ui.screen.authentication.model.UserModel

data class LoginState(
    val isLoading: Boolean = false,
    val loginSuccessful: Boolean = false,
    val loginError: String? = null,
    val loginResult: UserModel? = null,
    val email: String = "",
    val password: String = ""
)