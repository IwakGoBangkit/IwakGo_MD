package com.bangkit.fishery.ui.screen.home

import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bangkit.fishery.BuildConfig
import com.bangkit.fishery.R
import com.bangkit.fishery.ui.components.MenuFishItem
import com.bangkit.fishery.ui.components.SectionText
import com.bangkit.fishery.ui.screen.home.model.ImageResult
import com.bangkit.fishery.util.DummyFishItem.fishItem
import com.bangkit.fishery.util.createImageFile
import com.bangkit.fishery.util.toFile
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun HomeScreen(
    navigateToFishItem: (fishName: String) -> Unit,
    onImageSelected: (ImageResult) -> Unit,
    modifier: Modifier = Modifier
) {
    HomeContent(
        navigateToFishItem = navigateToFishItem,
        onImageSelected = {
            onImageSelected(
                ImageResult(it)
            )
        },
        modifier = modifier
    )
}

@Composable
fun HomeContent(
    navigateToFishItem: (fishName: String) -> Unit,
    onImageSelected: (File) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(top = 24.dp, start = 8.dp)
    ) {
        SectionText(stringResource(R.string.tips_cultivation))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = modifier
        ) {
            items(fishItem, key = { it.id }) { fish ->
                MenuFishItem(
                    menuFishModel = fish,
                    modifier = modifier.clickable {
                        navigateToFishItem(fish.title)
                    }
                )
            }
        }

        SectionText(stringResource(R.string.scan_fish))

        ScanFish(
            onImageSelected = onImageSelected
        )
    }
}


@Composable
fun ScanFish(
    onImageSelected: (File) -> Unit,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val file = context.createImageFile()

    val uri = FileProvider.getUriForFile(
        context,
        BuildConfig.APPLICATION_ID + ".provider",
        file
    )

    val launcherPickPhoto = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
    ) { result ->
        if (result != null) {
            scope.launch {
                onImageSelected(
                    result.toFile(context)
                )
            }
        }
    }

    val launcherTakePhoto = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { result ->
        if (result) {
            onImageSelected(
                uri.toFile(context)
            )
        }
    }

    val launcherPermission = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { result ->
        if (result) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            launcherTakePhoto.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }



    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .shadow(8.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.scanning),
            contentDescription = null
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.background)
                .padding(8.dp)
        ) {
            OutlinedButton(
                onClick = {
                    launcherPickPhoto.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                modifier = modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_upload),
                    contentDescription = stringResource(R.string.upload)
                )
                Text(
                    text = stringResource(R.string.upload),
                    modifier = modifier.padding(start = 4.dp)
                )
            }

            Button(
                onClick = {
                    val checkSelfPermission = ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.CAMERA
                    )
                    if (checkSelfPermission == PERMISSION_GRANTED) {
                        launcherTakePhoto.launch(uri)
                    } else {
                        launcherPermission.launch(Manifest.permission.CAMERA)
                    }
                },
                modifier = modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_camera),
                    contentDescription = stringResource(R.string.take_photo)
                )
                Text(
                    text = stringResource(R.string.take_photo),
                    modifier = modifier.padding(start = 4.dp)
                )
            }
        }
    }
}

