package com.bangkit.fishery_app.data.source.remote.response

data class FishSeedResponse(
	val pemilihanBenih: PemilihanBenih
)

data class KontenItem(
	val step: Int,
	val deskripsi: String,
	val judul: String
)

data class PemilihanBenih(
	val konten: List<KontenItem>,
	val title: String,
	val gambar: String
)

