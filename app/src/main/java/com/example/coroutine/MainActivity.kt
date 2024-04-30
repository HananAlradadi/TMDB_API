package com.example.coroutine

import android.os.Bundle
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import com.example.coroutine.data.UIState
import com.example.coroutine.data.di.Constant.MOVIE_BASE_URL
import com.example.coroutine.data.di.Constant.MOVIE_IMAGE_BASE_URL
import com.example.coroutine.data.imageSize
import com.example.coroutine.model.PopularMovies
import com.example.coroutine.ui.theme.CoroutineTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel by viewModels<PopularMovies>()
        super.onCreate(savedInstanceState)
        setContent {
            CoroutineTheme {
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
            }
        }
    }
}
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoroutineTheme {
        Greeting("Android")
    }
}