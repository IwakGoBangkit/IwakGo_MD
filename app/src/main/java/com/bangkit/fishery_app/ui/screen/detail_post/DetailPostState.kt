package com.bangkit.fishery_app.ui.screen.detail_post


import androidx.compose.runtime.mutableStateListOf
import com.bangkit.fishery_app.data.model.CommentModel
import com.bangkit.fishery_app.data.model.PostModel
import com.bangkit.fishery_app.data.source.remote.response.CommentResponse

data class DetailPostState(
    val isLoading: Boolean = false,
    val detailPost: PostModel? = null,
    val listComment: List<CommentModel> = mutableStateListOf(),
    val postCommentResult: CommentResponse? = null,
    val errorMessage: String? = null,
    val inputComment: String = "",
    val username: String = "",
    val profilePhoto: String = "",
    val isCommentSuccessful: Boolean = false,
)