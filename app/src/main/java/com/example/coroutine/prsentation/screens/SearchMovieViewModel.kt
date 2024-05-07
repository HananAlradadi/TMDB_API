package com.example.coroutine.prsentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.coroutine.model.Results
import com.example.coroutine.domain.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchMovieViewModel @Inject constructor(private val searchMovieUseCase: SearchMovieUseCase):
    ViewModel() {


    var searchMovieState: MutableStateFlow<PagingData<Results>> =
        MutableStateFlow(PagingData.empty())

    fun getArtWorks(query: String) {
            viewModelScope.launch {
                searchMovieUseCase.invoke(query).distinctUntilChanged()
                    .cachedIn(viewModelScope)
                    .collect {
                        searchMovieState.value = it
                    }
            }
        }
}





