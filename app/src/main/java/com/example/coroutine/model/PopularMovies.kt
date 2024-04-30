package com.example.coroutine.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutine.data.UIState
import com.example.coroutine.data.remore.PopularMoviesusecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMovies @Inject constructor(private val popularMoviesusecase : PopularMoviesusecase) :ViewModel() {

    var popularMoviesState : MutableState<UIState<SearchResponse>> = mutableStateOf(UIState.Loading())

    init {
        getArtWorks()
    }

    private fun getArtWorks() {
        viewModelScope.launch {
            when (val responses = popularMoviesusecase.invoke()){
                is UIState.Successful -> popularMoviesState.value = UIState.Successful(responses.data)
                is UIState.Error -> popularMoviesState.value = UIState.Error(responses.error)
                is UIState.Empty -> popularMoviesState.value = UIState.Empty(title =  responses.title)
                is UIState.Loading -> popularMoviesState.value = UIState.Loading()
            }
        }
    }
}