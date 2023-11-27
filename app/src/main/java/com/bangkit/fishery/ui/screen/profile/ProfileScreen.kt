package com.bangkit.fishery.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bangkit.fishery.R
import com.bangkit.fishery.ui.components.IconButtonTextHorizontal
import com.bangkit.fishery.ui.components.SectionText
import com.bangkit.fishery.util.MenuSetting.menuSetting
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ProfileScreen(
    moveToEdit: (id: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val systemUiController = rememberSystemUiController()

    val primary = MaterialTheme.colorScheme.primary
    val background = MaterialTheme.colorScheme.background

    DisposableEffect(Unit) {
        systemUiController.apply {
            setStatusBarColor(primary)
        }
        onDispose {
            systemUiController.apply {
                setStatusBarColor(background)
            }
        }
    }

    ProfileContent(
        moveToEdit = moveToEdit,
        modifier = modifier
    )
}

@Composable
fun ProfileContent(
    moveToEdit: (id: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        UserProfile()
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
        ) {
            SettingButton(
                moveToEdit = moveToEdit
            )
        }
    }
}

@Composable
fun UserProfile(
    modifier: Modifier = Modifier
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.boarding),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(160.dp)
                    .clip(CircleShape)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.name_user),
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = modifier.size(8.dp))
                Text(
                    text = stringResource(R.string.email_user),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun SettingButton(
    moveToEdit: (id: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp)
    ) {
        SectionText(
            title = stringResource(R.string.account),
            modifier = modifier.padding(top = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            items(menuSetting, key = { it.id }) { menu ->
                IconButtonTextHorizontal(
                    icon = painterResource(menu.icon),
                    title = stringResource(menu.title),
                    modifier = modifier.clickable {
                        moveToEdit(menu.id)
                    }
                )
            }
        }

        IconButtonTextHorizontal(
            icon = painterResource(R.drawable.ic_logout),
            title = stringResource(R.string.logout),
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp)
                .clickable { }
        )
    }
}