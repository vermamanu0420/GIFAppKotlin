package com.example.gifappkotlin.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gifappkotlin.GifApplication
import com.example.gifappkotlin.R
import com.example.gifappkotlin.databinding.FragmentMainBinding
import com.example.gifappkotlin.model.GifData
import com.example.gifappkotlin.view.ui.main.adapters.GifsListAdapter
import com.example.gifappkotlin.view.ui.main.adapters.OnItemClickListener
import com.example.gifappkotlin.viewmodel.GifsViewModel
import com.example.gifappkotlin.viewmodel.GifsViewModelFactory
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
            override fun onUnFavClick(item: GifData?) {
            }
            override fun onFavClick(item: GifData?) {
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(root.context)
        recyclerView.adapter = adapter

        observerViewModel()

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