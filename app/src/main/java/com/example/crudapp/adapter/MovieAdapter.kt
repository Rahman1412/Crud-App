package com.example.crudapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crudapp.R
import com.example.crudapp.data.Search

class MovieAdapter : PagingDataAdapter<Search, MovieAdapter.MovieViewHolder>(MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Search>() {
            override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean =
                oldItem.imdbID == newItem.imdbID

            override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean =
                oldItem == newItem
        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val movieNameTextView: TextView = itemView.findViewById(R.id.movieName)
        private val movieImageView: ImageView = itemView.findViewById(R.id.overlapping_image)
        private val movieYearView: TextView = itemView.findViewById(R.id.movieYear)

        fun bind(movie: Search) {
            movieNameTextView.text = movie.Title
            movieYearView.text = movie.Year
            Glide.with(itemView.context)
                .load(movie.Poster)
                .placeholder(R.drawable.ic_edit)
                .error(R.drawable.ic_edit)
                .into(movieImageView)
        }
    }
}