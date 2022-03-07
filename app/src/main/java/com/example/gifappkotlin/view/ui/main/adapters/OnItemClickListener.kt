package com.example.gifappkotlin.view.ui.main.adapters

import com.example.gifappkotlin.model.GifData

interface OnItemClickListener {
    fun onUnFavClick(item: GifData?)
    fun onFavClick(item: GifData?)
}
