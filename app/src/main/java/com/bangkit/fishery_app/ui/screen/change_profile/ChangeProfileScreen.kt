package com.bangkit.fishery_app.ui.screen.change_profile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.ui.components.SectionText
import com.bangkit.fishery_app.ui.screen.authentication.model.UserData
import kotlinx.coroutines.launch

@Composable
fun ChangeProfileScreen(
    user: UserData?
) {
    ChangeProfileContent(
        user = user
    )
}

@Composable
fun ChangeProfileContent(
    modifier: Modifier = Modifier,
    user: UserData?
) {

    val scope = rememberCoroutineScope()

    val launcherPickPhoto = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
    ) { result ->
        if (result != null) {
            scope.launch {

            }
        }
    }

    var name by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SectionText(title = stringResource(R.string.change_profile))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = user?.photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(240.dp)
                    .clip(CircleShape)
            )

            Button(
                onClick = {
                    launcherPickPhoto.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.upload_photo_profile)
                )
            }

            OutlinedTextField(
                value = user?.username ?: "",
                onValueChange = { newName -> name = newName },
                label = { Text(text = stringResource(R.string.username)) },
                keyboardOptions = KeyboardOptions.Default,
                shape = RoundedCornerShape(24.dp),
                modifier = modifier.fillMaxWidth()
            )

            Spacer(modifier = modifier.size(16.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.save)
                )
            }
        }
    }
}