package com.bangkit.fishery_app.data.source.remote.response

data class FishHarvestResponse(
	val panen: List<PanenItem>
)

data class PanenItem(
	val konten: String,
	val gambar: String
)

