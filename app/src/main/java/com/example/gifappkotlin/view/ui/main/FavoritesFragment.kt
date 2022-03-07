package com.example.gifappkotlin.view.ui.main

import com.example.gifappkotlin.view.ui.main.adapters.FavouritesGridAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gifappkotlin.GifApplication
import com.example.gifappkotlin.database.FavouriteGif
import com.example.gifappkotlin.databinding.FragmentFavoritesBinding
import com.example.gifappkotlin.view.ui.main.adapters.OnItemClickListener
import com.example.gifappkotlin.viewmodel.GifsViewModel
import com.example.gifappkotlin.viewmodel.GifsViewModelFactory
import java.util.ArrayList
import javax.inject.Inject


class FavoritesFragment : Fragment() {


    private lateinit var adapter: FavouritesGridAdapter
    private lateinit var gifsViewModel: GifsViewModel
    private lateinit var binding: FragmentFavoritesBinding

    @Inject
    lateinit var gifViewModelFactory: GifsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity?.application as GifApplication).applicationComponent.inject(this)
        gifsViewModel = ViewModelProvider(this, gifViewModelFactory).get(GifsViewModel::class.java)

        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView: RecyclerView = binding.favGifsList
        adapter = FavouritesGridAdapter(ArrayList(), object : OnItemClickListener {

            override fun onUnFavClick(item: FavouriteGif) {
                Toast.makeText(activity, "Removing from favorites", Toast.LENGTH_LONG).show()
                gifsViewModel.DeleteFavourite(item)
            }

            override fun onFavClick(item: FavouriteGif) {
                TODO("Not yet implemented")
            }
        })
        recyclerView.layoutManager = GridLayoutManager(root.context, 2)
        recyclerView.adapter = adapter
        observerViewModel()
        return root
    }

    private fun observerViewModel() {
        gifsViewModel.gifsFavouriteGifLiveData.observe(viewLifecycleOwner) { items ->
            if (items == null || items.size === 0) binding.favouriteNoData.visibility =
                View.VISIBLE else binding.favouriteNoData.visibility =
                View.GONE
            adapter.updateImages(items)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FavoritesFragment()
    }
}