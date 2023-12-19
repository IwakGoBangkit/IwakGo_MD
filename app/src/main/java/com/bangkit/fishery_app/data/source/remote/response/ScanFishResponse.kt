package com.bangkit.fishery_app.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ScanFishResponse(
	@field:SerializedName("prediction")
	val prediction: String
)
