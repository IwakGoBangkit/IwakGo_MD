package com.bangkit.fishery_app.ui.screen.change_profile

data class ChangeProfileState (
    val name: String = "",
    val profileChangeSuccessful: Boolean = false,
    val profileChangeError: String? = null
)