package com.example.movies.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movies.data.model.MovieDetails
import com.example.movies.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsViewModel (private val movieRepository : MovieDetailsRepository, movieId: Int)  : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val  movieDetailsDetails : LiveData<MovieDetails> by lazy {
        movieRepository.fetchSingleMovieDetails(compositeDisposable,movieId)
    }

    val networkState : LiveData<NetworkState> by lazy {
        movieRepository.getMovieDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
