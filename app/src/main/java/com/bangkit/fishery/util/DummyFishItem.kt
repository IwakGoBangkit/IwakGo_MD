package com.bangkit.fishery.util

import com.bangkit.fishery.R
import com.bangkit.fishery.ui.model.MenuFishModel

object DummyFishItem {
    val fishItem = listOf(
        MenuFishModel(
            id = "tilapia_fish",
            image = R.drawable.mujahir,
            title = "Mujahir"
        ),
        MenuFishModel(
            id = "catfish",
            image = R.drawable.lele,
            title = "Lele"
        ),
        MenuFishModel(
            id = "gurame_fish",
            image = R.drawable.gurame,
            title = "Gurame"
        ),
        MenuFishModel(
            id = "pangas_fish",
            image = R.drawable.patin,
            title = "Patin"
        )


    )
}