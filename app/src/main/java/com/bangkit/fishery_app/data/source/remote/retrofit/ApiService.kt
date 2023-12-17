package com.bangkit.fishery_app.data.source.remote.retrofit

import com.bangkit.fishery_app.data.source.remote.response.FishResponseItem
import com.bangkit.fishery_app.data.source.remote.response.PostResponse
import com.bangkit.fishery_app.data.source.remote.response.ResultsItem
import com.bangkit.fishery_app.data.source.remote.response.ScanResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path


interface ApiService {
    @GET("home/ikan")
    suspend fun getFishMenu() : List<FishResponseItem>

    @GET("marketplace/allPosts")
    suspend fun getPosts(): List<ResultsItem>

    @GET("marketplace/posts/{id}")
    suspend fun getDetailPost(
        @Path("id") id: Int
    ): PostResponse

    @Multipart
    @POST("marketplace/addPost")
    suspend fun addPost(
        @Part("username") username: RequestBody?,
        @Part("userProfilePhoto") userProfilePhoto: RequestBody?,
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part("location") location: RequestBody,
        @Part("phoneNumber") phoneNumber: RequestBody,
        @Part("price") price: RequestBody,
        @Part photo: MultipartBody.Part?,
    ): PostResponse

    @Multipart
    @POST("home/upload-image")
    suspend fun scanFish(
        @Part fishImage: MultipartBody.Part? = null
    ): ScanResponse

    @GET("marketplace/search")
    suspend fun searchPost(
        @Path("query") query: String
    ): List<PostResponse>
}