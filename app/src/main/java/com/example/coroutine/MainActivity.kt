package com.example.coroutine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.coroutine.data.UIState
import com.example.coroutine.data.di.Constant.MOVIE_IMAGE_BASE_URL
import com.example.coroutine.data.imageSize
import com.example.coroutine.model.PopularMovies
import com.example.coroutine.prsentation.screens1.onBouding.onBoardingViewModel
import com.example.coroutine.prsentation.screens1.onBouding.startPage
import com.example.coroutine.ui.theme.CoroutineTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel by viewModels<PopularMovies>()
        super.onCreate(savedInstanceState)
        setContent {

            CoroutineTheme {
                Surface() {

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "UI1") {
                        composable("UI1") { UI1(navController) }
                        composable("UI2") { UI2(navController) }
                        composable("UI3") { UI3(navController) }
                        composable("UI4") { UI4(viewModel) }

                    }
                }
            }
        }

    }
}

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
                            model = MOVIE_IMAGE_BASE_URL + imageSize.w780 +"/" +it.backdropPath,
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



    }

@Composable
fun UI3(navController: NavHostController, ) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
    ) {
        // nedd to add Composable in item or items
        item {
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                modifier = Modifier
                    .height(375.dp)
                    .width(375.dp),
                painter = painterResource(id = R.drawable.horrormovieamico),
                contentDescription = "",
                alignment = Alignment.Center,

                )
        }
        item {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Tailored Just for You",
                    color = Color.Black,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )

            }
        }


        item {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Personalize your experience! With movie recommendations and exclusive offers, enjoy the cinema your way.",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(50.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                addImage()

                Button(
                    onClick = { navController.navigate("UI4") },
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Lets's get started")
                }
            }
        }
    }

}

@Composable
fun UI1(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
    ) {
        // nedd to add Composable in item or items
        item {
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                modifier = Modifier
                    .height(375.dp)
                    .width(375.dp)
                ,painter = painterResource(id = R.drawable.movienightamico),
                contentDescription = "",
                alignment = Alignment.Center,

                )
        }
        item {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Welcome to CineSpectra!",
                    color = Color.Black,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )

            }
        }


        item {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Explore the latest movies, reserve the perfect seats, and experience the cinema in a whole new way.",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(50.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                addImage()

                Button(
                    onClick = { navController.navigate("UI2") },
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Next")
                }
            }
        }
    }

}

@Composable
fun UI2(navController: NavHostController, ) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
    ) {
        // nedd to add Composable in item or items
        item {
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                modifier = Modifier
                    .height(375.dp)
                    .width(375.dp),
                painter = painterResource(id = R.drawable.homecinema),
                contentDescription = "",
                alignment = Alignment.Center,

                )
        }
        item {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Quick and Easy Booking",
                    color = Color.Black,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )

            }
        }


        item {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Reserve your favorite seat in just a few steps. No waiting, no hassle!",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(50.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                addImage()

                Button(
                    onClick = { navController.navigate("UI3") },
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Next")
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


@Composable
fun addImage() {
    Image(
        painter = painterResource(id = R.drawable.brutalist_rotated__2_),
        contentDescription = "",
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}
