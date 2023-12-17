package com.bangkit.fishery_app.ui.screen.detail_post


import androidx.compose.runtime.mutableStateListOf
import com.bangkit.fishery_app.data.model.CommentModel
import com.bangkit.fishery_app.data.model.PostModel

data class DetailPostState(
    val isLoading: Boolean = false,
    val detailPost: PostModel? = null,
    val listComment: List<CommentModel> = mutableStateListOf(),
    val errorMessage: String? = null
)