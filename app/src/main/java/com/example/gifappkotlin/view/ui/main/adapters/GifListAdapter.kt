package com.example.gifappkotlin.view.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gifappkotlin.database.FavouriteGif
import com.example.gifappkotlin.databinding.GifItemBinding
import com.example.gifappkotlin.model.GifData
import com.example.gifappkotlin.utils.GlideUtil

class GifsListAdapter(
    private val gifDataList: MutableList<GifData>,
    listener: OnItemClickListener
) :
    RecyclerView.Adapter<GifsListAdapter.GifViewHolder>() {
    private val listener: OnItemClickListener = listener

    fun updateImages(newGifs: List<GifData>?) {
        gifDataList.clear()
        gifDataList.addAll(newGifs!!)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val gifItemBinding: GifItemBinding = GifItemBinding.inflate(layoutInflater, parent, false)
        return GifViewHolder(gifItemBinding)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.bind(gifDataList[position], listener)
    }

    override fun getItemCount(): Int {
        return gifDataList.size
    }

    inner class GifViewHolder(gifItemBinding: GifItemBinding) :
        RecyclerView.ViewHolder(gifItemBinding.root) {
        private val gifItemBinding: GifItemBinding = gifItemBinding
        fun bind(gifItem: GifData, listener: OnItemClickListener) {
            GlideUtil.loadGif(
                gifItemBinding.gifView,
                gifItem.images.downsized.url,
                GlideUtil.getProgressDrawable(gifItemBinding.gifView.context)
            )

            gifItemBinding.delete.visibility = View.GONE
            if (gifItem.IsFavourite) {
                gifItemBinding.unFavourite.visibility = View.GONE
                gifItemBinding.favourite.visibility = View.VISIBLE
            } else {
                gifItemBinding.favourite.visibility = View.GONE
                gifItemBinding.unFavourite.visibility = View.VISIBLE
            }

            if (gifItemBinding.favourite.visibility === View.VISIBLE) gifItemBinding.favourite.setOnClickListener { _ ->
                listener.onFavClick(
                    FavouriteGif(
                        0,
                        gifItem.id,
                        gifItem.images.downsized.url
                    )
                )
            }

            if (gifItemBinding.unFavourite.visibility === View.VISIBLE) gifItemBinding.unFavourite.setOnClickListener { _ ->
                listener.onUnFavClick(
                    FavouriteGif(0, gifItem.id, gifItem.images.downsized.url)
                )
            }
        }

    }

}
