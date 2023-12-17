package com.bangkit.fishery_app.ui.screen.seed_selection

import androidx.compose.runtime.mutableStateListOf
import com.bangkit.fishery_app.data.model.Method

data class SeedSelectionState(
    val isLoading: Boolean = false,
    val listMethodChooseSeed : List<Method> = mutableStateListOf(),
    val titleMenu: String? = "",
    val image: String? = "",
    val errorMessage: String? = ""
)