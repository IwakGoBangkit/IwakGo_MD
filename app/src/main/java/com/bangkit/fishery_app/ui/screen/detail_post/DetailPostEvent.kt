package com.bangkit.fishery_app.ui.screen.detail_post

sealed class DetailPostEvent {
    data class AddComment(
        val idPost: Int,
        val comment: String,
        val username: String,
        val profilePhoto: String,
    ) : DetailPostEvent()
    data class OnCommentChanged(val comment: String) : DetailPostEvent()
    data class SetLoadingState(val isLoading: Boolean) : DetailPostEvent()
}