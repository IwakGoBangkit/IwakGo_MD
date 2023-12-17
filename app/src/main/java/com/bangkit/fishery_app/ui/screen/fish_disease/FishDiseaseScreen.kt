package com.bangkit.fishery_app.ui.screen.fish_disease

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
fun FishDiseaseScreen(
    fish: String,
    viewModel: FishDiseaseViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = fish) {
        viewModel.getFishDisease(fish)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()


    FishDiseaseContent(
        listDisease = state.listDisease
    )
}

@Composable
fun FishDiseaseContent(
    modifier: Modifier = Modifier,
    listDisease: List<ContentModel>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(16.dp)
    ) {
        items(listDisease, key = {it.title}) {
            ItemContentModelWithTitle(
                title = it.title,
                image =it.image,
                description =it.description
            )
        }
    }
}