package com.bangkit.fishery_app.ui.screen.add_post

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.ui.components.LoadingDialog
import com.bangkit.fishery_app.util.toFile
import kotlinx.coroutines.launch

@Composable
fun AddPostScreen(
    modifier: Modifier = Modifier,
    viewModel: AddPostViewModel = hiltViewModel(),
    moveToMarket: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val launcherPickImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
    ) { uri: Uri? ->
        uri?.let {
            imageUri = it
            viewModel.viewModelScope.launch {
                viewModel.onEvent(AddPostEvent.OnImageChanged(it))
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(
            modifier = modifier
                .size(300.dp, 300.dp)
                .clickable {
                    launcherPickImage.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = state.image ?: Image(
                        painter = painterResource(id = R.drawable.upload),
                        contentDescription = null
                    ),
                    contentDescription = stringResource(R.string.upload_image),
                    contentScale = ContentScale.FillBounds,
                    modifier = modifier.fillMaxSize()
                )
            }
        }

        AddPostContent(
            modifier = modifier,
            title = state.title,
            location = state.location,
            phoneNumber = state.phoneNumber,
            price = state.price,
            description = state.description,
            onTitleChanged = {
                viewModel.viewModelScope.launch {
                    viewModel.onEvent(AddPostEvent.OnTitleChanged(it))
                }
            },
            onLocationChanged = {
                viewModel.viewModelScope.launch {
                    viewModel.onEvent(AddPostEvent.OnLocationChanged(it))
                }
            },
            onPhoneNumberChanged = {
                viewModel.viewModelScope.launch {
                    viewModel.onEvent(AddPostEvent.OnPhoneNumberChanged(it))
                }
            },
            onPriceChanged = {
                viewModel.viewModelScope.launch {
                    viewModel.onEvent(AddPostEvent.OnPriceChanged(it))
                }
            },
            onDescriptionChanged = {
                viewModel.viewModelScope.launch {
                    viewModel.onEvent(AddPostEvent.OnDescriptionChanged(it))
                }
            },
            successUpload = {
                viewModel.viewModelScope.launch {
                    viewModel.onEvent(AddPostEvent.AddPost(
                        image = imageUri?.toFile(context),
                        username = state.username,
                        profilePhoto = state.profilePhoto,
                        title = state.title,
                        location = state.location,
                        phoneNumber = state.phoneNumber,
                        price = state.price,
                        description = state.description,
                    ))
                }
            }
        )

        LaunchedEffect(state.addPostSuccessful) {
            if (state.addPostSuccessful) {
                moveToMarket()
                viewModel.onEvent(AddPostEvent.ResetState)
            }
        }

        if (state.isLoading) {
            LoadingDialog()
        }

    }

}

@Composable
fun AddPostContent(
    modifier: Modifier = Modifier,
    title: String,
    location: String,
    phoneNumber: String,
    price: String,
    description: String,
    onTitleChanged: (String) -> Unit,
    onLocationChanged: (String) -> Unit,
    onPhoneNumberChanged: (String) -> Unit,
    onPriceChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    successUpload: () -> Unit,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = title,
            onValueChange = {
                onTitleChanged(it)
            },
            label = { Text(stringResource(R.string.title)) },
            shape = RoundedCornerShape(24.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        OutlinedTextField(
            value = location,
            onValueChange = {
                onLocationChanged(it)
            },
            label = { Text(stringResource(R.string.location)) },
            shape = RoundedCornerShape(24.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                onPhoneNumberChanged(it)
            },
            label = { Text(stringResource(R.string.phone)) },
            shape = RoundedCornerShape(24.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        OutlinedTextField(
            value = price,
            onValueChange = {
                onPriceChanged(it)
            },
            label = { Text(stringResource(R.string.price)) },
            shape = RoundedCornerShape(24.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        OutlinedTextField(
            value = description,
            onValueChange = {
                onDescriptionChanged(it)
            },
            label = { Text(stringResource(R.string.description)) },
            shape = RoundedCornerShape(24.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Button(
            onClick = {
                successUpload()
            },
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