package com.bangkit.fishery_app.ui.screen.preservation

import androidx.compose.runtime.mutableStateListOf
import com.bangkit.fishery_app.data.model.Method

data class PreservationState(
    val isLoading : Boolean = false,
    val errorMessage: String? = "",
    val listPreservation: List<Method> = mutableStateListOf(),
    val title: String? = "",
    val image: String? = ""
)