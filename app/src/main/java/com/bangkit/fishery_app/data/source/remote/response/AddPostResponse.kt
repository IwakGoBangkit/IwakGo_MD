package com.bangkit.fishery_app.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AddPostResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean,

	@field:SerializedName("statusCode")
	val statusCode: Int
)
