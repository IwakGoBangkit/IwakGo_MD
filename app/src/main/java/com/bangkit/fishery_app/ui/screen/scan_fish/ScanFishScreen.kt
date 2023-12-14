package com.bangkit.fishery_app.ui.screen.scan_fish

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.ui.screen.home.model.ImageResult
import java.io.File

@Composable
fun ScanFishScreen(
    modifier: Modifier = Modifier,
    image: ImageResult?,
    onImageScanned: (image: ImageResult) -> Unit,
) {

    ScanFishContent(
        image = image,
        onImageScanned = {
            onImageScanned(
                ImageResult(
                    it
                )
            )
        },
        modifier = modifier
    )
}

@Composable
fun ScanFishContent(
    modifier: Modifier = Modifier,
    image: ImageResult?,
    onImageScanned: (image: File) -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(top = 128.dp, start = 32.dp, end = 32.dp, bottom = 32.dp)
    ) {
        AsyncImage(
            model = image?.imageFile,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.size(328.dp)
        )

        Button(
            onClick = {
                image?.imageFile?.let { onImageScanned(it) }
            },
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.upload))
        }
    }
}


