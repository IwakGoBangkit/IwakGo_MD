package com.bangkit.fishery.util

import com.bangkit.fishery.R
import com.bangkit.fishery.data.model.FishMenu

object DummyMethodCultivation {
    val methodCultivation = listOf(
        FishMenu(
            id = "pool",
            image = R.drawable.pool_selection,
            title = "Pemilihan Kolam"
        ),
        FishMenu(
            id = "seed",
            image = R.drawable.seed_selection,
            title = "Pemilihan Benih"
        ),
        FishMenu(
            id = "preservation",
            image = R.drawable.preservation,
            title = "Pemeliharaan"
        ),
        FishMenu(
            id = "harvest",
            image = R.drawable.harvest,
            title = "Panen"
        ),
    )
}