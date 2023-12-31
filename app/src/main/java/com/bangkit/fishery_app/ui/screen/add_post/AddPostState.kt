package com.bangkit.fishery_app.ui.screen.add_post

import android.net.Uri
import com.bangkit.fishery_app.data.source.remote.response.PostResponse

data class AddPostState(
    val image: Uri? = null,
    val title: String = "",
    val location: String = "",
    val phoneNumber: String = "",
    val price: String = "",
    val description: String = "",
    val isLoading: Boolean = false,
    val addPostSuccessful: Boolean = false,
    val addPostError: String? = null,
    val postResult: PostResponse? = null,
    val username: String = "",
    val profilePhoto: String = "",
)