package com.sdzshn3.moviedb.retrofit

import com.sdzshn3.moviedb.dataClasses.TopRatedMoviesResponse
import com.sdzshn3.moviedb.dataClasses.UpcomingMoviesResponse
import com.sdzshn3.moviedb.other.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query(API_KEY) apiKey: String): Response<UpcomingMoviesResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query(API_KEY) apiKey: String): Response<TopRatedMoviesResponse>
}