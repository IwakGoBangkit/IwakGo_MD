package com.bangkit.fishery_app.data.source.remote.retrofit

import androidx.room.Dao
import com.bangkit.fishery_app.data.source.remote.response.FishResponse
import com.bangkit.fishery_app.data.source.remote.response.FishResponseItem
import com.bangkit.fishery_app.data.source.remote.response.PostResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query


@Dao
interface ApiService {
    @GET("home/ikan")
    suspend fun getFishMenu() : List<FishResponseItem>

    @GET("marketplace/posts")
    suspend fun getPosts(
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null
    ): PostResponse

    @Multipart
    @POST("marketplace/add-post")
    suspend fun addPost(
        @Part photo: MultipartBody.Part? = null,
        @Part ("title") title: RequestBody? = null,
        @Part ("date") data: RequestBody? = null,
        @Part ("description") description: RequestBody? = null,
        @Part ("location") location: RequestBody? = null,
        @Part ("phoneNumber") phoneNumber: RequestBody? = null,
        @Part ("price") price: RequestBody? = null,
    )

}