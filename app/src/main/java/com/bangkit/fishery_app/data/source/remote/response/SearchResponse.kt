package com.bangkit.fishery_app.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SearchResponseItem(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("photo")
	val photo: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("userProfilePhoto")
	val userProfilePhoto: String,

	@field:SerializedName("username")
	val username: String
)
