package com.example.coroutine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.pm.ShortcutInfoCompat.Surface
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.coroutine.data.UIState
import com.example.coroutine.data.di.Constant.MOVIE_IMAGE_BASE_URL
import com.example.coroutine.data.imageSize
import com.example.coroutine.model.PopularMovies
import com.example.coroutine.prsentation.navigation.NavGraph
import com.example.coroutine.prsentation.navigation.popUpToTop
import com.example.coroutine.prsentation.screens1.onBouding.BottomNavigationItem
import com.example.coroutine.prsentation.screens1.onBouding.Screens
import com.example.coroutine.prsentation.screens1.onBouding.onBoardingViewModel
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


                val navController = rememberNavController()

                var showBottomBar by rememberSaveable { mutableStateOf(true) }
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                showBottomBar = when (navBackStackEntry?.destination?.route) {
                    Screens.onBoarding.route -> false // on this screen bottom bar should be hidden
                    else -> true // in all other cases show bottom bar
                }
                val navigationSelectedItem by remember { mutableIntStateOf(0) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (showBottomBar) {
                            NavigationBar {
                                BottomNavigationBar(navigationSelectedItem, navController)
                            }
                        }
                    }
                ) { paddingValues ->
                    //We need to setup our NavHost in here
                    Box(modifier = Modifier.padding(paddingValues)) {
                        NavGraph(navController)
                    }
                }
            }
        }
    }

    @Composable
    private fun RowScope.BottomNavigationBar(
        navigationSelectedItem: Int,
        navController: NavHostController
    ) {
        var navigationSelectedItem1 = navigationSelectedItem
        BottomNavigationItem().BottomNavigationItems()
            .forEachIndexed { index, navigationItem ->
                //iterating all items with their respective indexes
                NavigationBarItem(
                    selected = index == navigationSelectedItem1,
                    label = {
                        Text(navigationItem.label)
                    },
                    icon = {
                        Icon(
                            navigationItem.icon,
                            contentDescription = navigationItem.label
                        )
                    },
                    onClick = {
                        navigationSelectedItem1 = index
                        navController.navigate(navigationItem.route) {
                            popUpToTop(navController)
                        }
                    }
                )
            }
    }
}