package com.bangkit.fishery.ui.screen.profile

import com.bangkit.fishery.ui.screen.authentication.model.UserData

data class ProfileState(
    val userData: UserData? = null,
    val isLoggedOut: Boolean = false
)