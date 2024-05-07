package com.example.coroutine.prsentation.screens1.onBouding

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
