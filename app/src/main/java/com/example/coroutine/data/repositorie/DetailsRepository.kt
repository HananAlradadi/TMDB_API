package com.example.coroutine.data.repositorie

import com.example.coroutine.data.UIState
import com.example.coroutine.data.remore.detailsAPI
import com.example.coroutine.model.Movie
import javax.inject.Inject

class detailsRepository  @Inject constructor(private val detailsApi: detailsAPI
) {
    suspend fun getMovieDetails(movieId: Int): UIState<Movie> {
        return try {
            val response = detailsApi.getMovieDetails(movieId = movieId)
            if (response.isSuccessful && response.body() != null) {
                UIState.Successful(response.body())
            } else {
                UIState.Empty(massage = response.message().toString())
            }
        } catch (e: Exception) {
            UIState.Error(e.message.toString())
        }
    }
}


