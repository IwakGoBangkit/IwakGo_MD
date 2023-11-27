package com.bangkit.fishery.ui.screen.seed_selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.bangkit.fishery.R

@Composable
fun SeedSelectionScreen(
    fish: String
) {
    SeedSelectionContent(fishName = fish)
}

@Composable
fun SeedSelectionContent(
    fishName: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.seed_selection) + " " + stringResource(R.string.fish) + " " + fishName
        )
    }
}