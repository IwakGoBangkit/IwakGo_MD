package com.bangkit.fishery_app.util

import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.data.model.FishMenu
import com.bangkit.fishery_app.ui.model.FishMenuDetail

object DummyMenuFish {

    val fishMenu = listOf(
        FishMenuDetail(
            id = "cultivation",
            image = R.drawable.method_cultivation,
            title = "Cara Budidaya",
        ),
        FishMenuDetail(
            id = "feed",
            image = R.drawable.feed_recommendation,
            title = "Rekomendasi Pakan",
        ),
        FishMenuDetail(
            id = "disease",
            image = R.drawable.fish_disease,
            title = "Penyakit",
        ),
    )
}