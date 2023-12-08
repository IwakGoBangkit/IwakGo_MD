package com.bangkit.fishery_app.util

import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.ui.model.SettingMenuModel

object MenuSetting {

    val menuSetting = listOf(
        SettingMenuModel(
            id = "edit_profile",
            icon = R.drawable.ic_profile,
            title = R.string.change_profile
        ),
        SettingMenuModel(
            id = "edit_email",
            icon = R.drawable.ic_email,
            title = R.string.change_email
        ),
        SettingMenuModel(
            id = "edit_password",
            icon = R.drawable.ic_password_lock,
            title = R.string.change_password
        ),
        SettingMenuModel(
            id = "privacy_safety",
            icon = R.drawable.ic_privacy_safety,
            title = R.string.privacy_security
        )

    )
}