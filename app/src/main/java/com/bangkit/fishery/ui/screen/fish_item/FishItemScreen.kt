package com.bangkit.fishery.ui.screen.fish_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bangkit.fishery.R
import com.bangkit.fishery.ui.components.CardHorizontal
import com.bangkit.fishery.ui.components.SectionText
import com.bangkit.fishery.util.DummyMenuFish.fishMenu

@Composable
fun FishItemScreen(
    fishName: String,
    moveToCultivation : (fishName: String, idMenu: String) -> Unit,
    modifier: Modifier = Modifier
) {
    FishItemContent(
        fish = fishName,
        moveToCultivation = moveToCultivation,
        modifier = modifier.padding(top = 8.dp)
    )
}

@Composable
fun FishItemContent(
    fish: String,
    moveToCultivation: (fishName: String, idMenu: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        SectionText(title = stringResource(R.string.fish) + " $fish")

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier.padding(horizontal = 8.dp)
        ) {
            items(fishMenu, key = {it.id}) { menu ->
                CardHorizontal(
                    image = painterResource(menu.image),
                    title = menu.title,
                    modifier = modifier.clickable {
                        moveToCultivation(fish, menu.id)
                    }
                )
            }
        }

    }
}