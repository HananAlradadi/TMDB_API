package com.example.coroutine.data.remore

import com.example.coroutine.BuildConfig
import com.example.coroutine.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface searchMovieApi {

    @GET("3/search/multi")
    suspend fun getUpcoming(
        @Query("api_key")
        apiKey:String = BuildConfig.TMDB_API_KEY,
        @Query("language")
        language:String = "en-US",
        @Query("page")
        page : Int = 1,
        @Query("query")
        query :String     ,
    ) : Response<SearchResponse>

}