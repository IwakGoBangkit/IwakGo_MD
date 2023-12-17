package com.bangkit.fishery_app.ui.screen.pool_selection

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
import com.bangkit.fishery_app.ui.components.ItemContentModelWithTitle

@Composable
fun PoolSelectionScreen(
    fish: String,
    viewModel: PoolSelectionViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = fish) {
        viewModel.getPoolFish(fish)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    PoolSelectionContent(
        listPool = state.listPool
    )
}

@Composable
fun PoolSelectionContent(
    modifier: Modifier = Modifier,
    listPool: List<ContentModel>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(16.dp)
    ) {
        items(listPool, key = {it.title}) {
            ItemContentModelWithTitle(
                title = it.title,
                image = it.image,
                description = it.description
            )
        }
    }
}