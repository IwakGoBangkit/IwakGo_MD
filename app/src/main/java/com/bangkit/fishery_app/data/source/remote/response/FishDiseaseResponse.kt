package com.bangkit.fishery_app.data.source.remote.response

data class FishDiseaseResponse(
	val penyakit: List<PenyakitItem>
)

data class PenyakitItem(
	val konten: String,
	val title: String,
	val gambar: String
)

