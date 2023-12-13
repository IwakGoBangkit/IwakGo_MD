package com.bangkit.fishery_app.ui.screen.add_post

data class AddPostState(
    val title : String = "",
    val location : String = "",
    val phoneNumber : String = "",
    val price : String = "",
    val description : String = "",
    val isLoading : Boolean = false,
    val addPostSuccessful : Boolean = false,
    val addPostError : String? = null,
)