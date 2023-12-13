package com.bangkit.fishery_app.data.repository

import com.bangkit.fishery_app.data.model.PostModel
import com.bangkit.fishery_app.data.source.remote.retrofit.ApiConfig
import com.bangkit.fishery_app.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor() : PostRepository {

    override suspend fun getAllPost(): Flow<Result<List<PostModel>>> = flow {
        emit(Result.Loading())
        try {
            val response = ApiConfig.getApiService().getPosts().map {
                PostModel(
                    idPost = it.id,
                    username = it.username,
                    userPhoto = it.userProfilePhoto,
                    date = it.date,
                    image = it.photo,
                    title = it.title,
                    location = it.location,
                    phone = it.phoneNumber,
                    price = it.price.toString(),
                    description = it.description,
                )
            }
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getDetailPost(id: Int): Flow<Result<PostModel>> = flow {
        emit(Result.Loading())
        try {
            val response = ApiConfig.getApiService().getDetailPost(id).let {
                PostModel(
                    idPost = it.id,
                    username = it.username,
                    userPhoto = it.userProfilePhoto,
                    date = it.date,
                    image = it.photo,
                    title = it.title,
                    location = it.location,
                    phone = it.phoneNumber,
                    price = it.price.toString(),
                    description = it.description,
                )
            }
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

}