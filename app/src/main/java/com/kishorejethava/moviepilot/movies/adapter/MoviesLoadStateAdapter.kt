package com.kishorejethava.moviepilot.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kishorejethava.moviepilot.R
import com.kishorejethava.moviepilot.databinding.ItemMovieLoadStateFooterViewBinding
import com.kishorejethava.moviepilot.utils.toVisibility

class MoviesLoadStateAdapter(private val retry : () -> Unit) :
    LoadStateAdapter<MoviesLoadStateAdapter.MoviesLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: MoviesLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MoviesLoadStateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_load_state_footer_view, parent, false)
        val binding = ItemMovieLoadStateFooterViewBinding.bind(view)
        return MoviesLoadStateViewHolder(binding, retry)

    }

    inner class MoviesLoadStateViewHolder(private val binding: ItemMovieLoadStateFooterViewBinding,
    retry : ()-> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.retryButton.setOnClickListener {
                retry.invoke()
            }
        }
        fun bind(loadState : LoadState){
            if(loadState is LoadState.Error){
                binding.errorMsg.text = loadState.error.localizedMessage
            }
            binding.progressBar.visibility  = toVisibility(loadState is LoadState.Loading)
        }
    }
}