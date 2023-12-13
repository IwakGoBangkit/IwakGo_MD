package com.bangkit.fishery_app.data.repository

import com.bangkit.fishery_app.data.model.FishMenu
import com.bangkit.fishery_app.util.Result
import kotlinx.coroutines.flow.Flow

interface FishRepository {
    suspend fun getAllFishMenu(): Flow<Result<List<FishMenu>>>
}