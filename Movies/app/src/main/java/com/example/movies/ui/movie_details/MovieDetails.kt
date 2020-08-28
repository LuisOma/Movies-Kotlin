package com.example.movies.ui.movie_details

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.movies.BuildConfig.IMAGE_BASE_URL
import com.example.movies.R
import com.example.movies.data.api.TheMovieDBClient
import com.example.movies.data.api.TheMovieDBInterface
import com.example.movies.data.model.MovieDetails
import com.example.movies.data.repository.NetworkState
import com.example.movies.utils.utils
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetails : AppCompatActivity() {
    private lateinit var detailsViewModel: MovieDetailsViewModel
    private lateinit var movieRepository: MovieDetailsRepository

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        image_back?.setOnClickListener {
            onBackPressed()
        }
        val movieId: Int = intent.getIntExtra("id",1)
        val apiService : TheMovieDBInterface = TheMovieDBClient.getClient()
        movieRepository = MovieDetailsRepository(apiService)
        detailsViewModel = getViewModel(movieId)

        detailsViewModel.movieDetailsDetails.observe(this, Observer {
            bindUI(it)
        })

        detailsViewModel.networkState.observe(this, Observer {
            progress_bar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun bindUI(it: MovieDetails){
        movie_title.text = it.title
        if(!it.tagline.isEmpty()) {
            movie_tagline.text = it.tagline
        } else {
            movie_tagline.visibility = View.GONE
        }
        movie_release_date.text = utils.getStringFormatted(it?.releaseDate.toString())
        movie_rating.text = it.rating.toString()
        var genresString = "";
        for (genre in it.genres) {
            genresString += genre.name + ", "
        }
        movie_gender.text = genresString.dropLast(2)
        movie_runtime.text = it.runtime.toString() + " minutos"
        movie_overview.text = it.overview
        val moviePosterURL = IMAGE_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(iv_movie_poster);
    }

    private fun getViewModel(movieId:Int): MovieDetailsViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieDetailsViewModel(movieRepository,movieId) as T
            }
        })[MovieDetailsViewModel::class.java]
    }
}
