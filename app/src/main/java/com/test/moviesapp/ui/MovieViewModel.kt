package com.test.moviesapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.test.moviesapp.commons.utils.ApiResult
import com.test.moviesapp.ui.model.MovieUIModel
import com.test.moviesapp.usescases.GetPopularMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val getPopularMoviesUseCase: GetPopularMoviesUseCase) : ViewModel() {

    private val _movies = MutableStateFlow<MovieUIModel>(MovieUIModel.Loading)
    val movies: StateFlow<MovieUIModel> = _movies.asStateFlow()

    fun getMovies(context: Context) {
        viewModelScope.launch {
            _movies.value = MovieUIModel.Loading
            when (val result = getPopularMoviesUseCase.invoke()) {
                is ApiResult.Loading -> {
                    _movies.value = MovieUIModel.Loading
                }
                is ApiResult.Success -> {
                    _movies.value = MovieUIModel.Movies(result.data)
                }
                is ApiResult.Error -> {
                    _movies.value = MovieUIModel.Error(result.error.getMessage(context))
                }
            }
        }
    }

    class MovieViewModelFactory(
        private val getPopularMoviesUseCase: GetPopularMoviesUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
                return MovieViewModel(getPopularMoviesUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
