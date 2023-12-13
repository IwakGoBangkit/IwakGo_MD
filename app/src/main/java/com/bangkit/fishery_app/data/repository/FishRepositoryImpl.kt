package com.bangkit.fishery_app.data.repository

import com.bangkit.fishery_app.data.model.FishMenu
import com.bangkit.fishery_app.data.source.remote.retrofit.ApiConfig
import com.bangkit.fishery_app.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

}