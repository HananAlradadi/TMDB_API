package com.example.coroutine.domain

import com.example.coroutine.data.UIState
import com.example.coroutine.data.repositorie.detailsRepository
import com.example.coroutine.model.Movie
import javax.inject.Inject

class DetailsMoviesUseCase @Inject constructor(private val DetailsRepository : detailsRepository) {

    suspend operator fun invoke(movieId: Int) : UIState<Movie> {
        return DetailsRepository.getMovieDetails(movieId)
    }
}