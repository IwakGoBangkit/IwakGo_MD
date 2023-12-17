package com.bangkit.fishery_app.data.source.remote.response

data class FishPoolResponse(
	val pemilihanKolam: List<PemilihanKolamItem>
)

data class PemilihanKolamItem(
	val konten: String,
	val title: String,
	val gambar: String
)

