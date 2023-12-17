package com.bangkit.fishery_app.ui.screen.fish_disease

import androidx.compose.runtime.mutableStateListOf
import com.bangkit.fishery_app.data.model.ContentModel

data class FishDiseaseState(
    val isLoading: Boolean = false,
    val listDisease: List<ContentModel> = mutableStateListOf(),
    val errorMessage: String? = ""
)