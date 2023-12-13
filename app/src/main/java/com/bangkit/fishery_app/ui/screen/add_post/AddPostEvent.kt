package com.bangkit.fishery_app.ui.screen.add_post

sealed class AddPostEvent {

    data class OnTitleChanged(val title: String) : AddPostEvent()
    data class OnLocationChanged(val location: String) : AddPostEvent()
    data class OnPhoneNumberChanged(val phoneNumber: String) : AddPostEvent()
    data class OnPriceChanged(val price: String): AddPostEvent()
    data class OnDescriptionChanged(val description: String): AddPostEvent()
    data class SetLoadingState(val isLoading: Boolean) : AddPostEvent()
    object ResetState : AddPostEvent()
}