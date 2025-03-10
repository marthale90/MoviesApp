package com.test.moviesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.test.moviesapp.data.remote.repository.Repository
import com.test.moviesapp.databinding.ActivityMainBinding
import com.test.moviesapp.databinding.DialogMovieDetailBinding
import com.test.moviesapp.domain.MovieDomainModel
import com.test.moviesapp.ui.MovieViewModel
import com.test.moviesapp.ui.adapter.MoviesAdapter
import com.test.moviesapp.ui.model.MovieUIModel
import com.test.moviesapp.usescases.GetPopularMoviesUseCase
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val _gridColumns = 3
    private lateinit var binding: ActivityMainBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private val movieViewModel: MovieViewModel by viewModels {
        val repository = Repository()
        MovieViewModel.MovieViewModelFactory(GetPopularMoviesUseCase(repository))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpRecyclerView()
        observeMovies()
        movieViewModel.getMovies(this)
    }

    private fun setUpRecyclerView(movies: List<MovieDomainModel> = emptyList()) {
        moviesAdapter = MoviesAdapter(movies) {
            showMovieDetails(it)
        }
        with(binding) {
            val gridLayoutManager = GridLayoutManager(this@MainActivity, _gridColumns)
            rvMovies.layoutManager = gridLayoutManager
            rvMovies.adapter = moviesAdapter
        }
    }

    private fun observeMovies() {
        lifecycleScope.launch {
            movieViewModel.movies.collect { result ->
                when (result) {
                    is MovieUIModel.Loading -> {
                        binding.progressBar.isVisible = true
                    }

                    is MovieUIModel.Movies -> {
                        binding.progressBar.isVisible = false
                        moviesAdapter.updateMovies(result.movies)
                    }

                    is MovieUIModel.Error -> {
                        binding.progressBar.isVisible = false
                        showMessage(result.error)
                    }
                }
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showMovieDetails(movie: MovieDomainModel) {
        val dialogBinding = DialogMovieDetailBinding.inflate(LayoutInflater.from(this))
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        dialogBinding.apply {
            tvTitle.text = movie.title
            tvGenres.text = movie.genres.joinToString(", ")
            tvOverview.text = movie.overview
            tvReleaseDate.text =
                String.format(getString(R.string.release_date_label), movie.releaseDate)
            tvGenres.text = movie.genres.joinToString(", ")
            val imageUrl = "${EnvironmentConstants.IMAGE_BASE_URL}${movie.posterPath}"
            ivPoster.load(imageUrl) {
                crossfade(true)
            }
            btnClose.setOnClickListener { dialog.dismiss() }
        }
        dialog.show()
    }
}
