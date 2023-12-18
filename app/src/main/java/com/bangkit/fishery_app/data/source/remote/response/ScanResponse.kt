package com.bangkit.fishery_app.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ScanResponse(
	@field:SerializedName("imageUrl")
	val imageUrl: String,
	val kondisi: String,
)

