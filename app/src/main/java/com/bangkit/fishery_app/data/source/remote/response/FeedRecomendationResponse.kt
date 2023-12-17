package com.bangkit.fishery_app.data.source.remote.response

data class FeedRecommendationResponse(
	val rekomendasiPakan: List<RekomendasiPakanItem>
)

data class RekomendasiPakanItem(
	val konten: String,
	val title: String,
	val gambar: String
)

