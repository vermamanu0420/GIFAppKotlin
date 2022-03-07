package com.example.gifappkotlin.view.ui.main.adapters

import com.example.gifappkotlin.database.FavouriteGif

interface OnItemClickListener {
    fun onUnFavClick(item: FavouriteGif)
    fun onFavClick(item: FavouriteGif)
}
