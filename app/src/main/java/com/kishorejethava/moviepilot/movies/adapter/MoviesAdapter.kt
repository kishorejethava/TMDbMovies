package com.kishorejethava.moviepilot.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kishorejethava.moviepilot.databinding.ItemMovieListBinding
import com.kishorejethava.moviepilot.movies.model.Movie
import com.kishorejethava.moviepilot.movies.viewmodel.MovieListItemViewModel

class MoviesAdapter : PagingDataAdapter<Movie, MoviesAdapter.ViewHolder>(MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemMovieListBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            val viewModel = MovieListItemViewModel(movie)
            holder.bind(viewModel)
        }
    }

    inner class ViewHolder(var viewBinding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(viewModel: MovieListItemViewModel) {
            viewBinding.viewModel = viewModel

        }
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
}