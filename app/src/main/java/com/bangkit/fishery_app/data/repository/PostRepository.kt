package com.bangkit.fishery_app.data.repository

import com.bangkit.fishery_app.data.model.CommentModel
import com.bangkit.fishery_app.data.model.PostModel
import com.bangkit.fishery_app.data.source.remote.response.CommentResponse
import com.bangkit.fishery_app.data.source.remote.response.PostResponse
import com.bangkit.fishery_app.util.Result
import kotlinx.coroutines.flow.Flow
import java.io.File


interface PostRepository {

    suspend fun getAllPost(): Flow<Result<List<PostModel>>>

    suspend fun getDetailPost(idPost: Int): Flow<Result<PostModel>>

    suspend fun addPost(
        username: String?,
        userProfilePhoto: String?,
        title: String,
        description: String,
        location: String,
        phoneNumber: String,
        price: String,
        photo: File
    ): Flow<Result<PostResponse>>

    suspend fun searchPost(title: String): Flow<Result<List<PostModel>>>

    suspend fun getComment(idPost: Int): Flow<Result<List<CommentModel>>>

    suspend fun addComment(idPost: Int, username: String?, photoProfile: String?, comment: String?): Flow<Result<CommentResponse>>

}