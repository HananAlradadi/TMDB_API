package com.example.coroutine.data.repositorie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.coroutine.data.Pages.SearchPagingSource
import com.example.coroutine.data.remore.searchMovieApi
import com.example.coroutine.model.Results
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMovieRepository @Inject constructor(private val SearchMovieApi: searchMovieApi
)  {


    fun searchMovies(query: String): Flow<PagingData<Results>>{
        return Pager(
            config = PagingConfig(pageSize = 15, prefetchDistance = 2),
            pagingSourceFactory = {
                SearchPagingSource(SearchMovieApi, query)
            }
        ).flow
    }

}
