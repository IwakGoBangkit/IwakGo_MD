package com.bangkit.fishery_app.ui.screen.pool_selection

import androidx.compose.runtime.mutableStateListOf
import com.bangkit.fishery_app.data.model.ContentModel

data class PoolSelectionState(
    val isLoading: Boolean = false,
    val listPool : List<ContentModel> = mutableStateListOf(),
    val errorMessage: String? = ""
)