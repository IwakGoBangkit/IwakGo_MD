package com.bangkit.fishery_app.ui.screen.feed_recommendation

import androidx.compose.runtime.mutableStateListOf
import com.bangkit.fishery_app.data.model.ContentModel

data class FeedRecommendationState(
    val isLoading: Boolean = false,
    val errorMessage: String? = "",
    val list : List<ContentModel> = mutableStateListOf()
)