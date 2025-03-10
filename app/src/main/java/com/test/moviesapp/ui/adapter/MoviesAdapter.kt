package com.test.moviesapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.moviesapp.EnvironmentConstants
import com.test.moviesapp.databinding.ItemMovieBinding
import com.test.moviesapp.domain.MovieDomainModel

class MoviesAdapter(
    private var movies: List<MovieDomainModel>,
    private val onItemClick: (MovieDomainModel) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieDomainModel) {
            binding.movieTitle.text = movie.title
            val imageUrl = "${EnvironmentConstants.IMAGE_BASE_URL}${movie.posterPath}"
            binding.moviePoster.load(imageUrl) {
                crossfade(true)
            }

            itemView.setOnClickListener {
                onItemClick(movie)
            }
        }
    }

    fun updateMovies(newMovies: List<MovieDomainModel>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}
