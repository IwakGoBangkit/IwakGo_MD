package com.bangkit.fishery.ui.screen.change_profile

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bangkit.fishery.R
import com.bangkit.fishery.ui.screen.authentication.model.UserData

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

    var name by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = user?.photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth()
                .size(328.dp)
                .clip(CircleShape)
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.change_profile)
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