package com.example.coroutine.prsentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.coroutine.prsentation.screens.PopularMovies
import com.example.coroutine.prsentation.screens.PopularMoviesScreen
import com.example.coroutine.prsentation.screens1.onBouding.Screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.coroutine.prsentation.screens.DetailsViewMode
import com.example.coroutine.prsentation.screens.SearchMovieViewModel
import com.example.coroutine.prsentation.screens.DetailsScreen
import com.example.coroutine.prsentation.screens.SearchMoviesScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel1 : DetailsViewMode
) {
   // val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    )
    {

        /*
        composable(Screens.onBoarding.route) {
            //OnBoardingScreen(onBoardingViewModel, navController)
        }
       */
        composable(Screens.Home.route) {
            val viewModel = hiltViewModel<PopularMovies>()
            PopularMoviesScreen(navController, viewModel.popularMoviesState)
        }
        composable(Screens.Search.route) {
            Column(modifier = Modifier
                .fillMaxSize()) {

            }
            val viewModel= hiltViewModel<SearchMovieViewModel>()
            SearchMoviesScreen(navController , viewModel.searchMovieState){
                viewModel.getArtWorks(it)
            }

        }
        composable(Screens.profile.route) {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)) {

            }
        }
        composable("${Screens.MovieDetail.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })) {
            val idMovie = it.arguments?.getInt("id")
            if (idMovie != null) {
                val viewModel = hiltViewModel<DetailsViewMode>()
                DetailsScreen(idMovie, viewModel)
            }
        }

    }
}

fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive =  true
        saveState = true
    }
}