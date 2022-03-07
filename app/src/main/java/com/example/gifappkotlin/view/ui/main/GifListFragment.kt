package com.example.gifappkotlin.view.ui.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gifappkotlin.GifApplication
import com.example.gifappkotlin.R
import com.example.gifappkotlin.database.FavouriteGif
import com.example.gifappkotlin.databinding.FragmentMainBinding
import com.example.gifappkotlin.view.ui.main.adapters.GifsListAdapter
import com.example.gifappkotlin.view.ui.main.adapters.OnItemClickListener
import com.example.gifappkotlin.viewmodel.GifsViewModel
import com.example.gifappkotlin.viewmodel.GifsViewModelFactory
import java.lang.String
import java.util.ArrayList
import javax.inject.Inject

class GifListFragment : Fragment() {

    private lateinit var adapter: GifsListAdapter
    private lateinit var gifsViewModel: GifsViewModel

    private var _binding: FragmentMainBinding? = null

    @Inject
    lateinit var gifViewModelFactory: GifsViewModelFactory

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root

        (activity?.application as GifApplication).applicationComponent.inject(this)
        gifsViewModel = ViewModelProvider(this, gifViewModelFactory).get(GifsViewModel::class.java)

        val recyclerView = binding.gifsList

        adapter = GifsListAdapter(ArrayList(), object : OnItemClickListener {
            override fun onUnFavClick(item: FavouriteGif) {
                Toast.makeText(activity, "Adding to favorites", Toast.LENGTH_LONG).show()
                gifsViewModel.InsertFavourite(item)
            }
            override fun onFavClick(item: FavouriteGif) {
                Toast.makeText(activity, "Removing to favorites", Toast.LENGTH_LONG).show()
                gifsViewModel.DeleteFavouriteBYId(item.gifId)
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(root.context)
        recyclerView.adapter = adapter

        observerViewModel()

        gifsViewModel.fetchTrendingGifs(true)

        binding.searchButton.setOnClickListener { v ->
            gifsViewModel.fetchGifs(
                String.valueOf(
                    binding.searchTextview.text
                ), true
            )
        }

        binding.searchTextview.setOnEditorActionListener { _, actionId, event ->
            if (event != null && event.keyCode === KeyEvent.KEYCODE_ENTER || actionId === EditorInfo.IME_ACTION_DONE) {
                binding.searchButton.performClick()
            }
            false
        }

        binding.gifsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    if (binding.searchTextview.text.toString() != "")
                        gifsViewModel.fetchGifs(
                        binding.searchTextview.text.toString(),
                        false
                    ) else
                        gifsViewModel.fetchTrendingGifs(false)
                }
            }
        })

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(): GifListFragment {
            return GifListFragment().apply {
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observerViewModel() {
        gifsViewModel.gifsLiveData.observe(viewLifecycleOwner) { items ->
            binding.listError.visibility = View.GONE
            adapter.updateImages(items)
            binding.loadingView.visibility = View.GONE
            if (items == null || items.size === 0) {
                binding.listError.visibility = View.VISIBLE
                binding.listError.setText(R.string.loadingDataErrorMsg)
            }
        }
    }
}