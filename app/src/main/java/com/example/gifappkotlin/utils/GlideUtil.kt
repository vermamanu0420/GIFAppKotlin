package com.example.gifappkotlin.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.gifappkotlin.R

object GlideUtil {
    fun loadGif(view: ImageView, url: String?, progressDrawable: CircularProgressDrawable?) {
        val options = RequestOptions()
            .placeholder(progressDrawable)
            .error(R.mipmap.ic_launcher_round)
        Glide.with(view.context)
            .setDefaultRequestOptions(options)
            .asGif()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .load(url)
            .into(view)
    }

    fun loadGifCenterCrop(
        view: ImageView,
        url: String?,
        progressDrawable: CircularProgressDrawable?
    ) {
        val options = RequestOptions()
            .placeholder(progressDrawable)
            .error(R.mipmap.ic_launcher_round)
        Glide.with(view.context)
            .setDefaultRequestOptions(options)
            .asGif()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .load(url)
            .centerCrop()
            .into(view)
    }

    fun getProgressDrawable(context: Context?): CircularProgressDrawable {
        val progressDrawable = CircularProgressDrawable(context!!)
        progressDrawable.strokeWidth = 10f
        progressDrawable.centerRadius = 50f
        progressDrawable.start()
        return progressDrawable
    }
}