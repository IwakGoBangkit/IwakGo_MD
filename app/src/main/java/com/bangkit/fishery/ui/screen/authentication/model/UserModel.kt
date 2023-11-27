package com.bangkit.fishery.ui.screen.authentication.model

data class UserModel(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val photoUrl: String?
)