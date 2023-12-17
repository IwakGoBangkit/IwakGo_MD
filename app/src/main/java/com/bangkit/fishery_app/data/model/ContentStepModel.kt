package com.bangkit.fishery_app.data.model

data class ContentStepModel(
    val titleMenu: String,
    val image: String,
    val listMethodChooseSeed: List<Method>
)

data class Method(
    val step: Int,
    val title: String,
    val description: String
)