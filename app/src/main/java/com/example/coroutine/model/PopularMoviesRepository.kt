package com.example.coroutine.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.coroutine.data.Pages.MoviePagingSource
import com.example.coroutine.data.UIState
import com.example.coroutine.data.remore.movieAPI
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularMoviesRepository @Inject constructor(val movieApi : movieAPI
) {
/*
   suspend fun  getPopularMovies() : UIState<SearchResponse> {
      /ry {
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
       }*/

 fun  getPopularMovies() : Flow<PagingData<Results>> {
    return Pager(
        config = PagingConfig(pageSize = 15 , prefetchDistance = 2),
        pagingSourceFactory = {
            MoviePagingSource(movieApi)
        }

    ).flow


} }