package com.example.coroutine.model

import com.example.coroutine.data.UIState
import com.example.coroutine.data.remore.movieAPI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularMoviesRepository @Inject constructor(val movieApi : movieAPI
) {

    suspend fun  getPopularMovies() : UIState<SearchResponse> {
        try {
            val response = movieApi.getUpcoming()
            if (response.isSuccessful && response.body() != null){
                return UIState.Successful(response.body())

            }
            else {
                return UIState.Empty(massage = response.message().toString())
            }

        }
        catch (e:Exception){
            return  UIState.Error(e.message.toString())
        }
    }

}