package com.preeti.newsapp.presentation.base

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.preeti.newsapp.presentation.R
import com.preeti.newsapp.presentation.newssources.NewsSourcesRoute
import com.preeti.newsapp.presentation.start.ScreenType
import com.preeti.newsapp.presentation.start.StartingRoute
import com.preeti.newsapp.presentation.topheadline.TopHeadlineRoute

sealed class Route(val name: String) {
    data object StartingScreen : Route("startingScreen")
    data object TopHeadlineScreen : Route("topHeadlineScreen")
    data object NewsSourcesScreen : Route("newsSourcesScreen")
}

@Composable
fun NewsNavHost() {

    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController, startDestination = Route.StartingScreen.name
    ) {

        composable(route = Route.StartingScreen.name) {
            StartingRoute(onClick = {
                startScreen(it, navController)
            })
        }

        composable(route = Route.TopHeadlineScreen.name) {
            TopHeadlineRoute(titleAppBar = stringResource(R.string.top_headlines), onNewsClick = {
                openCustomChromeTab(context, it)
            }, onBackNavigation = {
                navController.popBackStack()
            })
        }

        composable(route = Route.NewsSourcesScreen.name) {
            NewsSourcesRoute(
                titleAppBar = stringResource(R.string.news_sources),
                onNewsSourceClick = {

                },
                onBackNavigation = {
                    navController.popBackStack()
                })
        }
    }
}

fun openCustomChromeTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, url.toUri())
}

fun startScreen(type: ScreenType, navController: NavController) {
    when (type) {
        ScreenType.TopHeadlines -> navController.navigate(Route.TopHeadlineScreen.name)
        ScreenType.NewsSources -> navController.navigate(Route.NewsSourcesScreen.name)
        ScreenType.Languages -> "nothing"
        ScreenType.Countries -> "nothing"
        ScreenType.Search -> "nothing"
        ScreenType.TwoLanguages -> "nothing"
    }
}

