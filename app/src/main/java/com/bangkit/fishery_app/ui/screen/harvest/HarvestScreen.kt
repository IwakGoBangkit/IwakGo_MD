package com.bangkit.fishery_app.ui.screen.harvest

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
import com.bangkit.fishery_app.data.model.HarvestContentModel
import com.bangkit.fishery_app.ui.components.ItemContentModel

@Composable
fun HarvestScreen(
    fish: String,
    viewModel: HarvestViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = fish) {
        viewModel.getFishHarvest(fish)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    HarvestContent(
        listHarvest = state.listHarvest
    )
}

@Composable
fun HarvestContent(
    modifier: Modifier = Modifier,
    listHarvest: List<HarvestContentModel>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(16.dp)
    ) {
        items(listHarvest) {
            ItemContentModel(
                image = it.image,
                description = it.description
            )
        }
    }
}