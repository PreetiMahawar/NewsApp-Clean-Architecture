package com.preeti.newsapp.presentation.base

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.preeti.newsapp.presentation.R
import com.preeti.newsapp.presentation.countries.CountriesRoute
import com.preeti.newsapp.presentation.languages.LanguagesRoute
import com.preeti.newsapp.presentation.languages.TwoLanguagesRoute
import com.preeti.newsapp.presentation.newsbycountry.NewsByCountryRoute
import com.preeti.newsapp.presentation.newsbylanguage.NewsByLanguageRoute
import com.preeti.newsapp.presentation.newsbylanguage.NewsByTwoLanguagesRoute
import com.preeti.newsapp.presentation.newsbysource.NewsBySourceRoute
import com.preeti.newsapp.presentation.newssources.NewsSourcesRoute
import com.preeti.newsapp.presentation.start.ScreenType
import com.preeti.newsapp.presentation.start.StartingRoute
import com.preeti.newsapp.presentation.topheadline.TopHeadlineRoute

sealed class Route(val name: String) {
    data object StartingScreen : Route("startingScreen")
    data object TopHeadlineScreen : Route("topHeadlineScreen")
    data object NewsSourcesScreen : Route("newsSourcesScreen")
    data object NewsBySourceScreen : Route("newsBySourceScreen/{sourceId}")
    data object LanguagesScreen : Route("languagesScreen")
    data object NewsByLanguageScreen : Route("newsByLanguageScreen/{languageId}")
    data object TwoLanguagesScreen : Route("twoLanguagesScreen")
    data object NewsByTwoLanguagesScreen : Route("newsByTwoLanguagesScreen/{languageId1}/{languageId2}")
    data object CountriesScreen : Route("countriesScreen")
    data object NewsByCountryScreen : Route("newsByCountryScreen/{countryId}")
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
                    navController.navigate("newsBySourceScreen/$it")
                },
                onBackNavigation = {
                    navController.popBackStack()
                })
        }

        composable(
            route = Route.NewsBySourceScreen.name, arguments = listOf(
                navArgument("sourceId") {
                    type = NavType.StringType
                })
        ) {
            val sourceId: String = it.arguments?.getString("sourceId") ?: ""
            NewsBySourceRoute(sourceId = sourceId, onNewsClick = {
                openCustomChromeTab(context, it)
            }, onBackNavigation = {
                navController.popBackStack()
            })
        }

        composable(route = Route.LanguagesScreen.name) {
            LanguagesRoute(onLanguageClick = {
                navController.navigate("newsByLanguageScreen/$it")
            }, onBackNavigation = {
                navController.popBackStack()
            })
        }

        composable(
            route = Route.NewsByLanguageScreen.name, arguments = listOf(
                navArgument("languageId") {
                    type = NavType.StringType
                })
        ) {
            val languageId: String = it.arguments?.getString("languageId") ?: ""
            NewsByLanguageRoute(languageId = languageId, onNewsClick = {
                openCustomChromeTab(context, it)
            }, onBackNavigation = {
                navController.popBackStack()
            })
        }

        composable(route = Route.TwoLanguagesScreen.name) {
            TwoLanguagesRoute(onLanguageSelect = { languageId1, languageId2 ->
                navController.navigate("newsByTwoLanguagesScreen/${languageId1}/${languageId2}")
            }, onBackNavigation = {
                navController.popBackStack()
            })
        }

        composable(
            route = Route.NewsByTwoLanguagesScreen.name,
            arguments = listOf(navArgument("languageId1") {
                type = NavType.StringType
            }, navArgument("languageId2") {
                type = NavType.StringType
            })
        ) {
            val languageId1 = it.arguments?.getString("languageId1") ?: ""
            val languageId2 = it.arguments?.getString("languageId2") ?: ""
            NewsByTwoLanguagesRoute(
                onNewsClick = {
                    openCustomChromeTab(context, it)
                }, onBackNavigation = {
                    navController.popBackStack()
                }, languageId1 = languageId1, languageId2 = languageId2
            )
        }

        composable(route = Route.CountriesScreen.name) {
            CountriesRoute(onCountryClick = {
                navController.navigate("newsByCountryScreen/$it")
            }, onBackNavigation = {
                navController.popBackStack()
            })
        }

        composable(
            route = Route.NewsByCountryScreen.name, arguments = listOf(
                navArgument("countryId") {
                    type = NavType.StringType
                })
        ) {
            val countryId: String = it.arguments?.getString("countryId") ?: ""
            NewsByCountryRoute(countryId = countryId, onNewsClick = {
                openCustomChromeTab(context, it)
            }, onBackNavigation = {
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
        ScreenType.Languages -> navController.navigate(Route.LanguagesScreen.name)
        ScreenType.TwoLanguages ->  navController.navigate(Route.TwoLanguagesScreen.name)
        ScreenType.Countries -> navController.navigate(Route.CountriesScreen.name)
        ScreenType.Search -> "nothing"
    }
}

