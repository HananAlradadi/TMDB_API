package com.example.coroutine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.coroutine.prsentation.screens.DetailsViewMode
import com.example.coroutine.prsentation.screens.PopularMovies
import com.example.coroutine.prsentation.navigation.NavGraph
import com.example.coroutine.prsentation.navigation.popUpToTop
import com.example.coroutine.prsentation.screens1.onBouding.BottomNavigationItem
import com.example.coroutine.prsentation.screens1.onBouding.Screens
import com.example.coroutine.ui.theme.CoroutineTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel by viewModels<PopularMovies>()
        val viewModel1 by viewModels<DetailsViewMode>()
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
                        NavGraph(navController , viewModel1)
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
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
    }
}