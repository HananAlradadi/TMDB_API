package com.example.coroutine.prsentation.screens1.onBouding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens (val route : String) {
    object onBoarding : Screens("on_boarding")
    object Home : Screens("popular_movie_screen")
    object Search : Screens("search_route")
    object profile : Screens("profile_route")


}

data class BottomNavigationItem(
    val label : String = "" ,
    val icon : ImageVector = Icons.Filled.Home ,
    val route : String = ""
) {
   fun BottomNavigationItems (): List<BottomNavigationItem>{
       return listOf(
           BottomNavigationItem(
               label = "Home" ,
               icon = Icons.Filled.Home ,
               route = Screens.Home.route
           ),
           BottomNavigationItem(
               label = "Search" ,
               icon = Icons.Filled.Search ,
               route = Screens.Search.route
           ),
           BottomNavigationItem(
               label = "Profile" ,
               icon = Icons.Filled.AccountCircle ,
               route = Screens.profile.route
           )
       )
   }
}
