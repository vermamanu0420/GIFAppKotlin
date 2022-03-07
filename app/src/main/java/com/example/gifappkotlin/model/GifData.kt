package com.example.gifappkotlin.model

data class GifData(
    val id: String,
    val images: Images,
    var IsFavourite: Boolean = false
)