package com.bangkit.fishery_app.data.repository

import com.bangkit.fishery_app.data.model.PostModel
import com.bangkit.fishery_app.util.Result
import kotlinx.coroutines.flow.Flow


interface PostRepository {

    suspend fun getAllPost(): Flow<Result<List<PostModel>>>

    suspend fun getDetailPost(id: Int): Flow<Result<PostModel>>

}