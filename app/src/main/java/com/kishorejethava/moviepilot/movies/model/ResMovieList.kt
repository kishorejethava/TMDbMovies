package com.kishorejethava.moviepilot.movies.model

import com.google.gson.annotations.SerializedName

data class ResMovieList(
    @field:SerializedName("page")
    var page : Int,

    @field:SerializedName("results")
    var results : List<Movie>,

    @field:SerializedName("total_results")
    var totalResults : Int,

    @field:SerializedName("total_pages")
    var totalPages : Int
)