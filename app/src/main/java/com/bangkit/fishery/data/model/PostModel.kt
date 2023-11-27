package com.bangkit.fishery.data.model

data class PostModel (
    val idPost: String,
    val username: String,
    val userPhoto: Int,
    val date: String,
    val image: Int,
    val title: String,
    val location: String,
    val phone: String,
    val price: String,
    val description: String
)