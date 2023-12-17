package com.bangkit.fishery_app.ui.screen.harvest


import androidx.compose.runtime.mutableStateListOf
import com.bangkit.fishery_app.data.model.HarvestContentModel

data class HarvestState(
    val isLoading: Boolean = false,
    val listHarvest: List<HarvestContentModel> = mutableStateListOf(),
    val errorMessage: String? = ""
)