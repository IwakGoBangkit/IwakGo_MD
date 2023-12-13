package com.bangkit.fishery_app.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class FishResponse(

    @field:SerializedName("FishResponse")
    val fishResponse: List<FishResponseItem>
)

data class FishResponseItem(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("nama")
    val nama: String,

    @field:SerializedName("id")
    val id: Int
)
