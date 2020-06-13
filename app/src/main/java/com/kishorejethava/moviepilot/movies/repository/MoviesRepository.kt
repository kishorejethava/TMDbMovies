package com.kishorejethava.moviepilot.movies.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kishorejethava.moviepilot.movies.model.Movie
import com.kishorejethava.moviepilot.webservice.ApiService
import kotlinx.coroutines.flow.Flow

class MoviesRepository(private val service: ApiService) {

    fun getMovies() : Flow<PagingData<Movie>>{
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { MoviesPagingSource(service)}
        ).flow

    }
}