package com.bangkit.fishery_app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ItemContentModelWithTitle(
    modifier: Modifier = Modifier,
    title: String,
    image : String,
    description: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SectionText(title = title)
        
        AsyncImage(
            model = image,
            contentDescription = image,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth()
                .height(216.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = description,
            textAlign = TextAlign.Justify
        )
    }
}