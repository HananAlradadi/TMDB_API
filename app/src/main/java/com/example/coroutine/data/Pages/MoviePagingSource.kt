package com.example.coroutine.data.Pages

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.coroutine.BuildConfig
import com.example.coroutine.data.remore.movieAPI
import com.example.coroutine.model.Results
import okio.IOException

class MoviePagingSource(private val movieapi: movieAPI,)
    : PagingSource<Int , Results>() {
    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
return state.anchorPosition   }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
       return try {
           val currentPage = params.key?: 1
           val movies = movieapi.getUpcoming(
               apiKey = BuildConfig.TMDB_API_KEY,
               page = currentPage
           )
           LoadResult.Page(
               data = movies.body()?.results.orEmpty(),
               prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey =  if (movies.body()?.results?.isEmpty() == true) null else movies.body()?.page!! +1
               )
       }
       catch (exception : IOException){
           return LoadResult.Error(exception)
       }
    }


}