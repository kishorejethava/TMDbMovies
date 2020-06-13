package com.kishorejethava.moviepilot.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kishorejethava.moviepilot.movies.model.Movie
import com.kishorejethava.moviepilot.movies.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class MovieListViewModel(private val moviesRepository: MoviesRepository) : ViewModel(){

    private var currentResult: Flow<PagingData<Movie>>? = null

    fun getMovies(): Flow<PagingData<Movie>> {
        val newResult: Flow<PagingData<Movie>> = moviesRepository.getMovies()
            .cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }

}