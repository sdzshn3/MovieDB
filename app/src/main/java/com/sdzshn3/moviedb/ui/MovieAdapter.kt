package com.sdzshn3.moviedb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sdzshn3.moviedb.dataClasses.Movie
import com.sdzshn3.moviedb.databinding.MovieItemLayoutBinding

class MovieAdapter : ListAdapter<Movie, MovieAdapter.ViewHolder>(diff_callback) {

    inner class ViewHolder(val binding: MovieItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val diff_callback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMovie = currentList[position]
        holder.binding.movieItem = currentMovie
    }

}