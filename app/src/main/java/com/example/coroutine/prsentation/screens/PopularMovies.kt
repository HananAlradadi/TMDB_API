package com.example.coroutine.prsentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.coroutine.data.remore.PopularMoviesusecase
import com.example.coroutine.model.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMovies @Inject constructor(private val popularMoviesusecase : PopularMoviesusecase) :ViewModel() {

    var popularMoviesState : MutableStateFlow<PagingData<Results>> = MutableStateFlow(PagingData.empty())

    init {
        getArtWorks()
    }

    private fun getArtWorks() {
        viewModelScope.launch {
            popularMoviesusecase.invoke().distinctUntilChanged()
                .cachedIn(viewModelScope).collect({
                    popularMoviesState.value = it
                })
        }
    }
}