package com.example.coroutine.data.remore

import com.example.coroutine.model.PopularMoviesRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class PopularMoviesusecase   @Inject constructor(private val popularMoviesRepository : PopularMoviesRepository) {

    suspend operator fun invoke() = popularMoviesRepository.getPopularMovies()
}