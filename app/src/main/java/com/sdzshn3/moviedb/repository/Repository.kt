package com.sdzshn3.moviedb.repository

import com.sdzshn3.moviedb.other.Resource
import com.sdzshn3.moviedb.retrofit.MoviesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(
    private val moviesApi: MoviesApi
) {

    suspend fun getUpcomingMovies(apiKey: String) = withContext(Dispatchers.IO) {
        try {
            val response = moviesApi.getUpcomingMovies(apiKey)
            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error("Something went wrong. Please try again", null)
            }
        } catch (e: Exception) {
            Resource.error("Something went wrong. Please try again", null)
        }
    }

    suspend fun getTopRatedMovies(apiKey: String) = withContext(Dispatchers.IO) {
        try {
            val response = moviesApi.getTopRatedMovies(apiKey)
            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error("Something went wrong. Please try again", null)
            }
        } catch (e: Exception) {
            Resource.error("Something went wrong. Please try again", null)
        }
    }
}