package com.bangkit.fishery_app.ui.screen.market

import androidx.compose.runtime.mutableStateListOf
import com.bangkit.fishery_app.data.model.PostModel

data class MarketState(
    val listPosts: List<PostModel> = mutableStateListOf(),
    val isLoading: Boolean = false,
    val errorMessage : String? = "",
    val title: String = "",
)