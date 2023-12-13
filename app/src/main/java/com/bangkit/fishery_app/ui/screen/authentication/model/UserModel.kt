package com.bangkit.fishery_app.ui.screen.authentication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class UserModel(
    val data: UserData?,
    val errorMessage: String?
)

@Parcelize
data class UserData(
    val userId: String = "",
    val username: String? = "",
    val email: String? = "",
    val photoUrl: String? = ""
): Parcelable