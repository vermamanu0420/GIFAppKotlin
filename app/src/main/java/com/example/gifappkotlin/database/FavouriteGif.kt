package com.example.gifappkotlin.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_gifs")
class FavouriteGif(
    @field:PrimaryKey(autoGenerate = true)
    var id: Int,
    @field:ColumnInfo(name = "gif_id")
    var gifId: String,
    @field:ColumnInfo(name = "gif_url")
    var gifUrl: String
)