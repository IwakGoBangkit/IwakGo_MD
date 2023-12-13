package com.bangkit.fishery_app.ui.screen.add_post

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bangkit.fishery_app.R
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun AddPostScreen() {
    AddPostContent()
}

@Composable
fun AddPostContent(
    modifier: Modifier = Modifier,
    viewModel: AddPostViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    var image: File? = null

    val launcherPickPhoto = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { result ->
        if (result != null) {
            scope.launch {
                image = result.toFile()
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            modifier = modifier
                .size(width = 328.dp, height = 280.dp)
                .clickable {
                    launcherPickPhoto.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = if(image == null) painterResource(R.drawable.upload) else image,
                    contentDescription = stringResource(R.string.upload_image),
                    contentScale = ContentScale.Crop,
                    modifier = modifier.size(width = 328.dp, height = 224.dp)
                )
                Text(
                    text = stringResource(R.string.upload_image),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(8.dp)
                )
            }
        }
        OutlinedTextField(
            value = state.title,
            onValueChange = { title ->
                viewModel.onEvent(AddPostEvent.OnTitleChanged(title))
            },
            label = { Text(stringResource(R.string.title)) },
            shape = RoundedCornerShape(24.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        OutlinedTextField(
            value = state.location,
            onValueChange = { location ->
                viewModel.onEvent(AddPostEvent.OnLocationChanged(location))
            },
            label = { Text(stringResource(R.string.location)) },
            shape = RoundedCornerShape(24.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        OutlinedTextField(
            value = state.phoneNumber,
            onValueChange = { phoneNumber ->
                viewModel.onEvent(AddPostEvent.OnPhoneNumberChanged(phoneNumber))
            },
            label = { Text(stringResource(R.string.phone)) },
            shape = RoundedCornerShape(24.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        OutlinedTextField(
            value = state.price,
            onValueChange = { price ->
                viewModel.onEvent(AddPostEvent.OnPriceChanged(price))
            },
            label = { Text(stringResource(R.string.price)) },
            shape = RoundedCornerShape(24.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        OutlinedTextField(
            value = state.description,
            onValueChange = { description ->
                viewModel.onEvent(AddPostEvent.OnDescriptionChanged(description))
            },
            label = { Text(stringResource(R.string.description)) },
            shape = RoundedCornerShape(24.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.send),
            )
        }
    }
}