package com.bangkit.fishery_app.ui.screen.detail_post


import com.bangkit.fishery_app.data.model.PostModel

data class DetailPostState(
    val isLoading: Boolean = false,
    val detailPost: PostModel? = null,
    val errorMessage: String? = null
)