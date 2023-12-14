package com.bangkit.fishery_app.ui.screen.scan_result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.ui.components.SectionText
import com.bangkit.fishery_app.ui.screen.home.model.ImageResult
import com.bangkit.fishery_app.ui.screen.scan_result.model.DetectionResult

@Composable
fun ScanResultScreen(
    image: ImageResult?,
    navigateToHome: () -> Unit,
    viewModel: ScanResultViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = image,) {
        image?.imageFile?.let { viewModel.scanFish(it) }
    }

    ScanResultContent(
        navigateToHome = navigateToHome,
        condition = state.condition,
        image = image,
    )
}

@Composable
fun ScanResultContent(
    modifier: Modifier = Modifier,
    image: ImageResult?,
    condition: String,
    navigateToHome: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        AsyncImage(
            model = image?.imageFile,
            contentDescription = image?.imageFile.toString(),
            modifier = modifier
                .size(328.dp)
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        SectionText(title = stringResource(R.string.fish_condition))
        
        Text(text = condition)

        OutlinedButton(
            onClick = navigateToHome,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            Text(text = stringResource(R.string.back_to_home))
        }
    }
}
