package com.bangkit.fishery_app.data.source.remote.response

data class DetailPostResponse(
	val data: Data,
	val message: String,
	val status: Boolean,
	val statusCode: Int
)

data class Data(
	val date: String,
	val phoneNumber: String,
	val price: Int,
	val photo: String,
	val description: String,
	val location: String,
	val id: Int,
	val title: String,
	val userProfilePhoto: String,
	val username: String
)

