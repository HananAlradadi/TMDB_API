package com.example.coroutine.domain

import androidx.paging.PagingData
import com.example.coroutine.data.repositorie.SearchMovieRepository
import com.example.coroutine.model.Results
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(private val searchMovieRepository : SearchMovieRepository) {
     operator fun invoke(query: String): Flow<PagingData<Results>> {
            return searchMovieRepository.searchMovies(query)
        }
    }
