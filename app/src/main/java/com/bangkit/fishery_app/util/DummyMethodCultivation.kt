package com.bangkit.fishery_app.util

import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.data.model.FishMenu
import com.bangkit.fishery_app.ui.model.FishMenuDetail

object DummyMethodCultivation {
    val methodCultivation = listOf(
        FishMenuDetail(
            id = "pool",
            image = R.drawable.pool_selection,
            title = "Pemilihan Kolam"
        ),
        FishMenuDetail(
            id = "seed",
            image = R.drawable.seed_selection,
            title = "Pemilihan Benih"
        ),
        FishMenuDetail(
            id = "preservation",
            image = R.drawable.preservation,
            title = "Pemeliharaan"
        ),
        FishMenuDetail(
            id = "harvest",
            image = R.drawable.harvest,
            title = "Panen"
        ),
    )
}