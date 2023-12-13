package com.bangkit.fishery_app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bangkit.fishery_app.data.model.FishMenu

@Composable
fun MenuFishItem(
    image: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = image,
            contentDescription = title,
            modifier = Modifier.size(96.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
        )
    }
}