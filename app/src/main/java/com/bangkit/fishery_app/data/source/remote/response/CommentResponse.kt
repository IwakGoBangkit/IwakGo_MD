package com.bangkit.fishery_app.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CommentResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean,

	@field:SerializedName("statusCode")
	val statusCode: Int
)

data class DataItem(

	@field:SerializedName("photoProfile")
	val photoProfile: String,

	@field:SerializedName("comment")
	val comment: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("username")
	val username: String
)
