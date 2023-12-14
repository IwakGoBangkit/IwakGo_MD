package com.bangkit.fishery_app.data.repository

import com.bangkit.fishery_app.data.model.PostModel
import com.bangkit.fishery_app.data.source.firebase.FirebaseAuth
import com.bangkit.fishery_app.data.source.remote.retrofit.ApiConfig
import com.bangkit.fishery_app.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : PostRepository {

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

    override suspend fun addPost(
        username: String?,
        userProfilePhoto: String?,
        title: String,
        description: String,
        location: String,
        phoneNumber: String,
        price: String,
        photo: File?
    ): Flow<Result<PostModel>> = flow {
        emit(Result.Loading())
        try {
            val user = firebaseAuth.getLoggedUser()
            val usernameBody = user?.username?.toRequestBody("text/plain".toMediaType())
            val userProfilePhotoBody = user?.photoUrl?.toRequestBody("text/plain".toMediaType())
            val titleBody = title.toRequestBody("text/plain".toMediaType())
            val descriptionBody = description.toRequestBody("text/plain".toMediaType())
            val locationBody = location.toRequestBody("text/plain".toMediaType())
            val phoneNumberBody = phoneNumber.toRequestBody("text/plain".toMediaType())
            val priceBody = price.toRequestBody("text/plain".toMediaType())

            val photoPart = photo?.let {
                val requestImageFile = it.asRequestBody("image/jpeg".toMediaType())
                MultipartBody.Part.createFormData(
                    "photo",
                    it.name,
                    requestImageFile
                )
            }
            val response = ApiConfig.getApiService().addPost(
                usernameBody,
                userProfilePhotoBody,
                titleBody,
                descriptionBody,
                locationBody,
                phoneNumberBody,
                priceBody,
                photoPart
            ).let {
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
