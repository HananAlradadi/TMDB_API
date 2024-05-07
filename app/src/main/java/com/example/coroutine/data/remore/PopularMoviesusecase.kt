package com.example.coroutine.data.remore

import androidx.paging.PagingData
import com.example.coroutine.data.repositorie.PopularMoviesRepository
import com.example.coroutine.model.Results
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Reusable
class PopularMoviesusecase   @Inject constructor(private val popularMoviesRepository : PopularMoviesRepository) {

     operator fun invoke() : Flow<PagingData<Results>>{
       return popularMoviesRepository.getPopularMovies()
    }


}