package com.sdzshn3.moviedb.dataClasses

import com.google.gson.annotations.SerializedName

data class UpcomingMoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Movie>,
    @SerializedName("dates")
    val dates: Date,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
)
