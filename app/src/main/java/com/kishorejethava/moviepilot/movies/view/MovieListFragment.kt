package com.kishorejethava.moviepilot.movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kishorejethava.moviepilot.databinding.FragmentMovieListBinding
import com.kishorejethava.moviepilot.movies.adapter.MoviesAdapter
import com.kishorejethava.moviepilot.movies.adapter.MoviesLoadStateAdapter
import com.kishorejethava.moviepilot.movies.viewmodel.MovieListViewModel
import com.kishorejethava.moviepilot.utils.EqualSpacingItemDecoration
import com.kishorejethava.moviepilot.utils.Injection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var viewModel: MovieListViewModel
    private var searchJob: Job? = null
    private val adapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory())
            .get(MovieListViewModel::class.java)
        binding = FragmentMovieListBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialize()
    }

    private fun initialize() {
        initAdapter()
        getMovies()
    }

    private fun initAdapter() {
        binding.rvMovies.addItemDecoration(EqualSpacingItemDecoration(20,20,20,20))
        binding.rvMovies.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvMovies.isNestedScrollingEnabled = true
        binding.rvMovies.adapter = adapter.withLoadStateFooter(
            footer = MoviesLoadStateAdapter { adapter.retry() }
        )
    }

    private fun getLayoutManager(): GridLayoutManager {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        /*layoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                Log.e("getItemViewType","${adapter.getItemViewType(position)}")
                return 2
                return when (adapter.getItemViewType(position)) {
                        adapter. -> 1
                        MyAdapter.VIEW_TYPES.Progress -> 2 //number of columns of the grid
                        else -> -1
                    }
            }
        }*/
        return layoutManager
    }

    private fun getMovies() {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getMovies().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}