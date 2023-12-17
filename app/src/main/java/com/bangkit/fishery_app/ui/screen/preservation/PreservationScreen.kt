package com.bangkit.fishery_app.ui.screen.preservation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import coil.compose.AsyncImage
import com.bangkit.fishery_app.data.model.Method
import com.bangkit.fishery_app.ui.components.ItemContentMethod
import com.bangkit.fishery_app.ui.components.SectionText

@Composable
fun PreservationScreen(
    fish: String,
    viewModel: PreservationViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = fish) {
        viewModel.getFishPreservation(fish)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    PreservationContent(
        titleMenu = state.title ?: "",
        image = state.image ?: "",
        listStep = state.listPreservation
    )
}

@Composable
fun PreservationContent(
    modifier: Modifier = Modifier,
    titleMenu: String,
    image: String,
    listStep: List<Method>
) {
    Column(
        modifier = modifier
    ) {
        SectionText(titleMenu)

        AsyncImage(
            model = image,
            contentDescription = image,
            modifier = modifier
                .fillMaxWidth()
                .height(216.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.padding(16.dp)
        ) {
            items(listStep, key = {it.step}) {
                ItemContentMethod(
                    step = it.step,
                    title = it.title,
                    description = it.description
                )
            }
        }
    }
}