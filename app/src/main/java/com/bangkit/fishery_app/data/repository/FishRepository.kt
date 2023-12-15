package com.bangkit.fishery_app.data.repository

import com.bangkit.fishery_app.data.model.FishMenu
import com.bangkit.fishery_app.data.source.remote.response.ScanResponse
import com.bangkit.fishery_app.util.Result
import kotlinx.coroutines.flow.Flow
import java.io.File

interface FishRepository {
    suspend fun getAllFishMenu(): Flow<Result<List<FishMenu>>>

    fun scanFish(imageFish: File): Flow<Result<String>>
}