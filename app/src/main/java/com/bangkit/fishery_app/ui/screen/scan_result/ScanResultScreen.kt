package com.bangkit.fishery_app.ui.screen.scan_result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.ui.components.SectionText
import com.bangkit.fishery_app.ui.screen.home.model.ImageResult
import com.bangkit.fishery_app.ui.screen.scan_result.model.DetectionResult

@Composable
fun ScanResultScreen(
    image: ImageResult?,
    viewModel: ScanResultViewModel = hiltViewModel()
) {

    ScanResultContent(
//        detectionResult = ,
        navigateToHome = {},
        image = image,
    )
}

@Composable
fun ScanResultContent(
    modifier: Modifier = Modifier,
    image: ImageResult?,
//    detectionResult: DetectionResult,
    navigateToHome: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        AsyncImage(
            model = image,
            contentDescription = image?.imageFile.toString()
        )

        SectionText(title = stringResource(R.string.fish_type))

        Text(
            text = "",
        )

        SectionText(title = stringResource(R.string.fish_condition))
        
        Text(text = "")
        
        SectionText(title = stringResource(R.string.fish_treatment))
        
        Text(text = "")

        OutlinedButton(
            onClick = navigateToHome
        ) {
            Text(text = stringResource(R.string.back_to_home))
        }
    }
}
