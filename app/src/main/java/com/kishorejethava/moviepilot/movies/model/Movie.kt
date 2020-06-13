package com.kishorejethava.moviepilot.movies.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @field:SerializedName("id")
    var id: Int,

    @field:SerializedName("original_title")
    var originalTitle: String,

    @field:SerializedName("poster_path")
    var posterPath: String?,

    @field:SerializedName("adult")
    var adult: Boolean,

    @field:SerializedName("overview")
    var overview: String,

    @field:SerializedName("release_date")
    var releaseDate: String,

    @field:SerializedName("genre_ids")
    var genreIds: ArrayList<Int>,

    @field:SerializedName("original_language")
    var originalLanguage: String,

    @field:SerializedName("title")
    var title: String,

    @field:SerializedName("backdrop_path")
    var backdropPath: String?,

    @field:SerializedName("popularity")
    var popularity: Float,

    @field:SerializedName("voteCount")
    var voteCount: Int,

    @field:SerializedName("video")
    var video: Boolean,

    @field:SerializedName("vote_average")
    var voteAverage: Float


)