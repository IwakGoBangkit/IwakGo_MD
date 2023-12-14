package com.bangkit.fishery_app.ui.screen.add_post

import android.net.Uri
import java.io.File

sealed class AddPostEvent {

    data class OnTitleChanged(val title: String) : AddPostEvent()
    data class OnLocationChanged(val location: String) : AddPostEvent()
    data class OnPhoneNumberChanged(val phoneNumber: String) : AddPostEvent()
    data class OnPriceChanged(val price: String): AddPostEvent()
    data class OnDescriptionChanged(val description: String): AddPostEvent()
    data class SetLoadingState(val isLoading: Boolean) : AddPostEvent()
    data class OnImageChanged(val image: Uri?): AddPostEvent()
    data class AddPost(
        val image: File?,
        val username: String,
        val profilePhoto: String,
        val title: String,
        val location: String,
        val phoneNumber: String,
        val price: String,
        val description: String,
    ) : AddPostEvent()
    object ResetState : AddPostEvent()
}