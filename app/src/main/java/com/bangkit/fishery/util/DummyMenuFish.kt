package com.bangkit.fishery.util

import com.bangkit.fishery.R
import com.bangkit.fishery.data.model.FishMenu

object DummyMenuFish {

    val fishMenu = listOf(
        FishMenu (
            id = "cultivation",
            image = R.drawable.method_cultivation,
            title = "Cara Budidaya"
        ),
        FishMenu (
            id = "feed",
            image = R.drawable.feed_recommendation,
            title = "Rekomendasi Pakan"
        ),
        FishMenu (
            id = "disease",
            image = R.drawable.fish_disease,
            title = "Penyakit"
        )
    )
}