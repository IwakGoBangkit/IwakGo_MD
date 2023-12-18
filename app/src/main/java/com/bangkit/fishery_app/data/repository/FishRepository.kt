package com.bangkit.fishery_app.data.repository

import com.bangkit.fishery_app.data.model.ContentModel
import com.bangkit.fishery_app.data.model.ContentStepModel
import com.bangkit.fishery_app.data.model.FishMenu
import com.bangkit.fishery_app.data.model.HarvestContentModel
import com.bangkit.fishery_app.data.source.remote.response.ScanFishResponse
import com.bangkit.fishery_app.util.Result
import kotlinx.coroutines.flow.Flow
import java.io.File

interface FishRepository {
    suspend fun getAllFishMenu(): Flow<Result<List<FishMenu>>>

    suspend fun getPoolSelection(name: String) : Flow<Result<List<ContentModel>>>

    suspend fun getSeedSelection(name: String) : Flow<Result<ContentStepModel>>

    suspend fun getPreservation(name: String) : Flow<Result<ContentStepModel>>

    suspend fun getFishHarvest(name: String) : Flow<Result<List<HarvestContentModel>>>

    suspend fun getFishFeedRecommendation(name: String) : Flow<Result<List<ContentModel>>>

    suspend fun getFishDisease(name: String) : Flow<Result<List<ContentModel>>>

    suspend fun scanFish(imageFish: File): Flow<Result<ScanFishResponse>>
}