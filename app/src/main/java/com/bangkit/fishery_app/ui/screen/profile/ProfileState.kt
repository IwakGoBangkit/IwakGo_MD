package com.bangkit.fishery_app.ui.screen.profile

import com.bangkit.fishery_app.ui.screen.authentication.model.UserData

data class ProfileState(
    val userData: UserData? = null,
    val isLoggedOut: Boolean = false
)