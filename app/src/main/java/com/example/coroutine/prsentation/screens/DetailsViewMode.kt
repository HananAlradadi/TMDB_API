package com.example.coroutine.prsentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutine.data.UIState
import com.example.coroutine.domain.DetailsMoviesUseCase
import com.example.coroutine.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel

class DetailsViewMode  @Inject constructor(private val detailsMoviesUseCase : DetailsMoviesUseCase) :
    ViewModel() {
    var detailsMoviesState : MutableStateFlow<UIState<Movie>> = MutableStateFlow(UIState.Loading())

    init {
        //getArtWorks()
    }

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            when (val responses = detailsMoviesUseCase.invoke(movieId)){
                is UIState.Successful -> detailsMoviesState.value = UIState.Successful(responses.data)
                is UIState.Successful -> detailsMoviesState.value = UIState.Successful(responses.data)
                is UIState.Error -> detailsMoviesState.value = UIState.Error(responses.error)
                is UIState.Empty -> detailsMoviesState.value = UIState.Empty(title =  responses.title)
                is UIState.Loading -> detailsMoviesState.value = UIState.Loading()

            }
        }
    }
}



