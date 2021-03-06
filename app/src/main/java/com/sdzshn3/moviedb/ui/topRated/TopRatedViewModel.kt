package com.sdzshn3.moviedb.ui.topRated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdzshn3.moviedb.BuildConfig
import com.sdzshn3.moviedb.dataClasses.TopRatedMoviesResponse
import com.sdzshn3.moviedb.other.Resource
import com.sdzshn3.moviedb.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _movies = MutableLiveData<Resource<TopRatedMoviesResponse>>()
    val movies: LiveData<Resource<TopRatedMoviesResponse>> = _movies

    init {
        loadMovies()
    }

    fun loadMovies() {
        _movies.postValue(Resource.loading(null))
        viewModelScope.launch {
            val result = repository.getTopRatedMovies(BuildConfig.API_KEY)
            _movies.postValue(result)
        }
    }
}