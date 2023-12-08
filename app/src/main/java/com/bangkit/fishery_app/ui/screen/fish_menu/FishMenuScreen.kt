package com.bangkit.fishery_app.ui.screen.fish_menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.ui.components.CardVertical
import com.bangkit.fishery_app.ui.components.SectionText
import com.bangkit.fishery_app.util.DummyMethodCultivation.methodCultivation

@Composable
fun FishMenuScreen(
    fish: String,
    id: String,
    moveToDetailContent: (fishName: String, idMenu:String, idCultivation: String) -> Unit,
    modifier: Modifier = Modifier
) {
    FishMenuContent(
        fishName = fish,
        idMenu = id,
        moveToDetailContent = moveToDetailContent,
        modifier = modifier.padding(top = 8.dp)
    )
}

@Composable
fun FishMenuContent(
    fishName: String,
    idMenu: String,
    moveToDetailContent: (fishName: String, idMenu:String, idCultivation: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        SectionText(
            title = stringResource(R.string.method_cultivation),
            modifier = modifier.padding(start = 8.dp, top = 8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
        ) {
            items(methodCultivation, key = { it.id }) { cultivation ->
                CardVertical(
                    image = painterResource(cultivation.image),
                    title = cultivation.title,
                    modifier = modifier.clickable {
                        moveToDetailContent(fishName, idMenu, cultivation.id)
                    },
                )
            }
        }
    }
}