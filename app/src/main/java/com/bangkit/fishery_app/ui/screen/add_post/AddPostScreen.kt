package com.bangkit.fishery_app.ui.screen.add_post

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bangkit.fishery_app.R

@Composable
fun AddPostScreen() {
    AddPostContent()
}

@Composable
fun AddPostContent(
    modifier: Modifier = Modifier
) {

    var title by remember {
        mutableStateOf("")
    }

    var location by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var price by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
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
                .clickable {  }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.upload),
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
            value = title,
            onValueChange = { newTitle -> title = newTitle},
            label = { Text(stringResource(R.string.title)) },
            shape = RoundedCornerShape(24.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        OutlinedTextField(
            value = location,
            onValueChange = { newLocation -> location = newLocation},
            label = { Text(stringResource(R.string.location)) },
            shape = RoundedCornerShape(24.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        OutlinedTextField(
            value = phone,
            onValueChange = { newPhoneNumber -> phone = newPhoneNumber },
            label = { Text(stringResource(R.string.phone)) },
            shape = RoundedCornerShape(24.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        OutlinedTextField(
            value = price,
            onValueChange = { newPrice -> price = newPrice},
            label = { Text(stringResource(R.string.price)) },
            shape = RoundedCornerShape(24.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        OutlinedTextField(
            value = description,
            onValueChange = { newDescription -> description = newDescription},
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