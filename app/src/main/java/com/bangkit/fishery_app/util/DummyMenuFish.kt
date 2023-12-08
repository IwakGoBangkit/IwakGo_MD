package com.bangkit.fishery_app.util

import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.data.model.FishMenu

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