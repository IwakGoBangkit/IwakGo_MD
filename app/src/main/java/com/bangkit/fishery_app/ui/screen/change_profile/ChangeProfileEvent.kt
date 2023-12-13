package com.bangkit.fishery_app.ui.screen.change_profile

import java.io.File

sealed class ChangeProfileEvent {
    data class OnEmailChanged(val name: String) : ChangeProfileEvent()

    data class OnPhotoChange(val image: File) : ChangeProfileEvent()

    data class SetLoadingState(val isLoading: Boolean) : ChangeProfileEvent()

    object ResetState : ChangeProfileEvent()
}