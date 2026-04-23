package com.preeti.newsapp.presentation.base

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.preeti.newsapp.presentation.start.StartingRoute

sealed class Route(val name: String) {
    data object StartingScreen : Route("startingScreen")
}

@Composable
fun NewsNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = Route.StartingScreen.name
    ) {

        composable(route = Route.StartingScreen.name) {
            StartingRoute(onClick = {

            })
        }
    }
}

