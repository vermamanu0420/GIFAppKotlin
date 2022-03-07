package com.example.gifappkotlin.model

data class GifsDataModel(
    val `data`: List<GifData>,
    val meta: Meta,
    val pagination: Pagination
)