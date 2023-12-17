package com.bangkit.fishery_app.ui.screen.feed_recommendation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit.fishery_app.data.model.ContentModel
import com.bangkit.fishery_app.ui.components.ItemContentModel

@Composable
fun FeedRecommendationScreen(
    fish: String,
    viewModel: FeedRecommendationViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = fish) {
        viewModel.getFeedRecommendation(fish)
    }
    val state by viewModel.state.collectAsStateWithLifecycle()

    FeedRecommendationContent(
        listFeed = state.list
    )
}

@Composable
fun FeedRecommendationContent(
    modifier: Modifier = Modifier,
    listFeed: List<ContentModel>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(16.dp)
    ) {
        items(listFeed, key = {it.title}) {
            ItemContentModel(
                image = it.image,
                description = it.description
            )
        }
    }
}