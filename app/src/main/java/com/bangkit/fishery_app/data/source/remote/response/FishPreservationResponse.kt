package com.bangkit.fishery_app.data.source.remote.response

data class FishPreservationResponse(
	val pemeliharaan: Pemeliharaan
)

data class KontenStepItem(
	val step: Int,
	val deskripsi: String,
	val judul: String
)

data class Pemeliharaan(
	val konten: List<KontenStepItem>,
	val title: String,
	val gambar: String
)

