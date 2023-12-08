package com.bangkit.fishery_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bangkit.fishery_app.ui.model.MenuFishModel

@Composable
fun MenuFishItem(
    menuFishModel: MenuFishModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(menuFishModel.image),
            contentDescription = menuFishModel.title,
            modifier = Modifier.size(96.dp)
        )
        Text(
            text = menuFishModel.title,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
        )
    }
}