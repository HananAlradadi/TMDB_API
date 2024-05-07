package com.example.coroutine.prsentation.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.coroutine.data.UIState

@Composable
fun DetailsScreen(movieId:Int ,viewModel : DetailsViewMode) {

    LaunchedEffect(movieId) {

        viewModel.getMovieDetails(movieId)
    }
    val result = viewModel.detailsMoviesState.collectAsState()
    when (val data = result.value){
        is UIState.Successful -> {

                    Text(text = data.toString())


        }
        is UIState.Empty -> {}
        is UIState.Error -> {}
        is UIState.Loading -> {}
    }
}



