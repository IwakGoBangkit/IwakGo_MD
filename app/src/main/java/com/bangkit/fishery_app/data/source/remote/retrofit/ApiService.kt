package com.bangkit.fishery_app.data.source.remote.retrofit

import com.bangkit.fishery_app.data.source.remote.response.CommentResponse
import com.bangkit.fishery_app.data.source.remote.response.DetailPostResponse
import com.bangkit.fishery_app.data.source.remote.response.FeedRecommendationResponse
import com.bangkit.fishery_app.data.source.remote.response.FishDiseaseResponse
import com.bangkit.fishery_app.data.source.remote.response.FishHarvestResponse
import com.bangkit.fishery_app.data.source.remote.response.FishPoolResponse
import com.bangkit.fishery_app.data.source.remote.response.FishPreservationResponse
import com.bangkit.fishery_app.data.source.remote.response.FishResponseItem
import com.bangkit.fishery_app.data.source.remote.response.FishSeedResponse
import com.bangkit.fishery_app.data.source.remote.response.PostResponse
import com.bangkit.fishery_app.data.source.remote.response.ScanResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path


interface ApiService {
    @GET("home/ikan")
    suspend fun getFishMenu() : List<FishResponseItem>

    @GET("home/ikan/{name}/cara-budidaya/pemilihan-kolam")
    suspend fun getFishPool(
        @Path("name") name: String
    ) : FishPoolResponse

    @GET("home/ikan/{name}/cara-budidaya/pemilihan-benih")
    suspend fun getFishSeed(
        @Path("name") name: String
    ) : FishSeedResponse

    @GET("home/ikan/{name}/cara-budidaya/pemeliharaan")
    suspend fun getFishPreservation(
        @Path("name") name: String
    ) : FishPreservationResponse

    @GET("home/ikan/{name}/cara-budidaya/panen")
    suspend fun getFishHarvest(
        @Path("name") name: String
    ) : FishHarvestResponse

    @GET("home/ikan/{name}/rekomendasi-pakan")
    suspend fun getFishFeedRecommendation(
        @Path("name") name: String
    ): FeedRecommendationResponse

    @GET("home/ikan/{name}/penyakit")
    suspend fun getFishDisease(
        @Path("name") name: String
    ): FishDiseaseResponse

    @GET("marketplace/allPosts")
    suspend fun getPosts(): PostResponse

    @GET("marketplace/posts/{id}")
    suspend fun getDetailPost(
        @Path("id") id: Int
    ): DetailPostResponse

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
    @POST("predict")
    suspend fun scanFish(
        @Part file: MultipartBody.Part?
    ): ScanResponse

    @GET("marketplace/search")
    suspend fun searchPost(
        @Path("query") query: String
    ): List<PostResponse>

    @GET("comment/{id}/getComment")
    suspend fun getComment(
        @Path("id") id: Int
    ): CommentResponse

    @POST("comment/{id}/addComment")
    suspend fun addComment(
        @Body requestBody: RequestBody,
        @Path("id") id: Int
    ): CommentResponse
}