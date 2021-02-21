package com.sdzshn3.moviedb.ui.upcomingMovies

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.sdzshn3.moviedb.R
import com.sdzshn3.moviedb.databinding.UpcomingMoviesFragmentBinding
import com.sdzshn3.moviedb.other.Status
import com.sdzshn3.moviedb.other.gone
import com.sdzshn3.moviedb.other.visible
import com.sdzshn3.moviedb.ui.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingMoviesFragment : Fragment(R.layout.upcoming_movies_fragment) {

    private val viewModel: UpcomingMoviesViewModel by viewModels()
    private lateinit var binding: UpcomingMoviesFragmentBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = UpcomingMoviesFragmentBinding.bind(view)

        movieAdapter = MovieAdapter()
        setupRecyclerView()
        viewModel.movies.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.ERROR -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    binding.centerMessageTextView.text = it.message
                    binding.centerMessageTextView.visible()
                }
                Status.LOADING -> {
                    binding.swipeRefreshLayout.isRefreshing = true
                    binding.centerMessageTextView.gone()
                }
                Status.SUCCESS -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    binding.centerMessageTextView.gone()
                    movieAdapter.submitList(it.data!!.results)
                }
            }
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadMovies()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
        } else {
            binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupRecyclerView() = binding.recyclerView.apply {
        val orientation = resources.configuration.orientation
        layoutManager = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(requireContext(), 4)
        } else {
            GridLayoutManager(requireContext(), 2)
        }
        adapter = movieAdapter
    }
}