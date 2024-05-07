package com.example.coroutine.data.remore

import com.example.coroutine.BuildConfig
import com.example.coroutine.model.Movie
import com.example.coroutine.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface detailsAPI {
    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key")
        apiKey:String = BuildConfig.TMDB_API_KEY,
        @Query("language")
        language:String = "en-US",

    ) : Response<Movie>
}