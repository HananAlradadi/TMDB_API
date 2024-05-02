package com.example.coroutine.prsentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coroutine.UI1
import com.example.coroutine.UI2
import com.example.coroutine.UI3
import com.example.coroutine.UI4
import com.example.coroutine.model.PopularMovies
import com.example.coroutine.prsentation.screens1.onBouding.Screens
import com.example.coroutine.prsentation.screens1.onBouding.onBoardingViewModel
import dagger.Component
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
){
    val OnBoardingViewModel : onBoardingViewModel = hiltViewModel()
    NavHost(
        navController = navController ,
        startDestination = OnBoardingViewModel.startDestination
    )
    {

        composable( Screens.onBoarding.route) { UI1(navController) }
        composable(Screens.PopularMovie.route) {
            val viewModel = hiltViewModel<PopularMovies>()
            UI4(viewModel)
        }

    }

    fun NavOptionsBuilder.popUpToTop(navController: NavController){
        popUpTo(navController.currentBackStackEntry?.destination?.route?: return){
            inclusive = true
        }
    }


}