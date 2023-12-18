package com.bangkit.fishery_app.data.repository

import com.bangkit.fishery_app.data.model.ContentModel
import com.bangkit.fishery_app.data.model.ContentStepModel
import com.bangkit.fishery_app.data.model.FishMenu
import com.bangkit.fishery_app.data.model.HarvestContentModel
import com.bangkit.fishery_app.data.model.Method
import com.bangkit.fishery_app.data.source.remote.response.ScanResponse
import com.bangkit.fishery_app.data.source.remote.retrofit.ApiConfig
import com.bangkit.fishery_app.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class FishRepositoryImpl @Inject constructor() : FishRepository {

    override suspend fun getAllFishMenu()  = flow {
        emit(Result.Loading())
        try {
            val response = ApiConfig.getApiService().getFishMenu().map {
                FishMenu(
                    id = it.id,
                    image = it.image,
                    title = it.nama
                )
            }
            emit(Result.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getPoolSelection(name: String) = flow {
        emit(Result.Loading())

        try {
            val response = ApiConfig.getApiService().getFishPool(name).pemilihanKolam.map {fish ->
                ContentModel(
                    title = fish.title,
                    image = fish.gambar,
                    description = fish.konten
                )
            }
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getSeedSelection(name: String) = flow {
        emit(Result.Loading())

        try {
            val response = ApiConfig.getApiService().getFishSeed(name).pemilihanBenih.let {fish ->
                ContentStepModel(
                    titleMenu = fish.title,
                    image = fish.gambar,
                    listMethodChooseSeed = fish.konten.map {
                        Method(
                            step = it.step,
                            title = it.judul,
                            description = it.deskripsi
                        )
                    }
                )
            }
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getPreservation(name: String) = flow {
        emit(Result.Loading())

        try {
            val response = ApiConfig.getApiService().getFishPreservation(name).pemeliharaan.let {fish ->
                ContentStepModel(
                    titleMenu = fish.title,
                    image = fish.gambar,
                    listMethodChooseSeed = fish.konten.map {
                        Method(
                            step = it.step,
                            title = it.judul,
                            description = it.deskripsi
                        )
                    }
                )
            }
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getFishHarvest(name: String) = flow {
        emit(Result.Loading())

        try {
            val response = ApiConfig.getApiService().getFishHarvest(name).panen.map {
                HarvestContentModel(
                    image = it.gambar,
                    description = it.konten
                )
            }
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getFishFeedRecommendation(name: String) = flow {
        emit(Result.Loading())

        try {
            val response = ApiConfig.getApiService().getFishFeedRecommendation(name).rekomendasiPakan.map {fish ->
                ContentModel(
                    title = fish.title,
                    image = fish.gambar,
                    description = fish.konten
                )
            }
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getFishDisease(name: String) = flow {
        emit(Result.Loading())

        try {
            val response = ApiConfig.getApiService().getFishDisease(name).penyakit.map {fish ->
                ContentModel(
                    title = fish.title,
                    image = fish.gambar,
                    description = fish.konten
                )
            }
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun scanFish(imageFish: File): Flow<Result<ScanResponse>>  = flow {
        val requestImageFile = imageFish.asRequestBody("image/jpg".toMediaType())
        val fileMultipart = MultipartBody.Part.createFormData(
            "image",
            imageFish.name,
            requestImageFile
        )

        emit(Result.Loading())

        try {
            val apiService = ApiConfig.getApiService()
            val response = apiService.scanFish(fileMultipart)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }

}