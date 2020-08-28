package com.example.movies.data.api

import com.example.movies.data.model.MovieDetails
import com.example.movies.data.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {

    @GET("3/movie/now_playing")
    fun getPopularMovie(@Query("page") page: Int): Single<MovieResponse>

    @GET("3/movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>
}
