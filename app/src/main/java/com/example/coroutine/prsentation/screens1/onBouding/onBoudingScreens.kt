package com.example.coroutine.prsentation.screens1.onBouding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.coroutine.data.UIState
import com.example.coroutine.data.di.Constant
import com.example.coroutine.data.imageSize
import com.example.coroutine.model.PopularMovies
/*
@OptIn(ExperimentalStdlibApi ::class)
@Composable
fun UI4(viewModel: PopularMovies) {
    when (val result = viewModel.popularMoviesState.value){
        is UIState.Successful -> {
            LazyColumn( modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.secondary)
            ) {
                items(result.data?.results.orEmpty()){
                    Text(text = it.title.orEmpty(),
                        modifier = Modifier.padding(12.dp)
                    )
                    //  Text(text = it.backdropPath.toString())
                    AsyncImage(
                        model = Constant.MOVIE_IMAGE_BASE_URL + imageSize.w780 +"/" +it.backdropPath,
                        contentDescription = "title image"
                    )


                }
            }
        }
        is UIState.Empty -> {}
        is UIState.Error -> {}
        is UIState.Loading -> {}
    }
    /*
                when (val result = viewModel.popularMoviesState.value){
                    is UIState.Successful -> {
                        LazyColumn( modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.secondary)
                            ) {
                            items(result.data?.results.orEmpty()){
                            Text(text = it.title.orEmpty(),
                                modifier = Modifier.padding(12.dp)
                                )
                              //  Text(text = it.backdropPath.toString())
                                AsyncImage(
                                    model = MOVIE_IMAGE_BASE_URL + imageSize.w780 +"/" +it.backdropPath,
                                    //model = "https://m.media-amazon.com/images/I/71Mxd-axWBL._AC_SL1500_.jpg",
                                    contentDescription = "title image"
                                )


                            }
                        }
              }
                    is UIState.Empty -> {}
                    is UIState.Error -> {}
                    is UIState.Loading -> {}
            }*/



}*/
