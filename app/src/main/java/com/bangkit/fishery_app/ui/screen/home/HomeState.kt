package com.bangkit.fishery_app.ui.screen.home

import androidx.compose.runtime.mutableStateListOf
import com.bangkit.fishery_app.data.model.FishMenu

data class HomeState(
    val isLoading : Boolean = false,
    val listFish : List<FishMenu> = mutableStateListOf(),
    val errorMessage: String? = null
)