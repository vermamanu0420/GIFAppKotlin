package com.example.gifappkotlin.view.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gifappkotlin.database.FavouriteGif
import com.example.gifappkotlin.databinding.GifItemBinding
import com.example.gifappkotlin.utils.GlideUtil

class FavouritesGridAdapter(
    private val gifFavList: MutableList<FavouriteGif>,
    listener: OnItemClickListener
) :
    RecyclerView.Adapter<FavouritesGridAdapter.GifViewHolder>() {
    private val listener: OnItemClickListener = listener
    fun updateImages(newGifs: List<FavouriteGif>?) {
        gifFavList.clear()
        gifFavList.addAll(newGifs!!)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val gifItemBinding: GifItemBinding = GifItemBinding.inflate(layoutInflater, parent, false)
        return GifViewHolder(gifItemBinding)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.bind(gifFavList[position], listener)
    }

    override fun getItemCount(): Int {
        return gifFavList.size
    }

    inner class GifViewHolder(gifItemBinding: GifItemBinding) :
        RecyclerView.ViewHolder(gifItemBinding.root) {
        private val gifItemBinding: GifItemBinding = gifItemBinding
        fun bind(gifItem: FavouriteGif, listener: OnItemClickListener) {
            GlideUtil.loadGifCenterCrop(
                gifItemBinding.gifView,
                gifItem.gifUrl,
                GlideUtil.getProgressDrawable(gifItemBinding.gifView.context)
            )
            gifItemBinding.favourite.visibility = View.GONE
            gifItemBinding.unFavourite.visibility = View.GONE
            gifItemBinding.delete.setOnClickListener { _ -> listener.onUnFavClick(gifItem) }
        }

    }

}

