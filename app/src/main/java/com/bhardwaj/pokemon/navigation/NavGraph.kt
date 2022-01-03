package com.bhardwaj.pokemon.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.bhardwaj.pokemon.presentation.screens.home.HomeScreen
import com.bhardwaj.pokemon.presentation.screens.search.SearchScreen
import com.bhardwaj.pokemon.presentation.screens.splash.SplashScreen
import com.bhardwaj.pokemon.presentation.screens.welcome.WelcomeScreen
import com.bhardwaj.pokemon.utils.Constants.DETAILS_ARGUMENT_KEY
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) { SplashScreen(navController = navController) }
        composable(route = Screen.Welcome.route) { WelcomeScreen(navController = navController) }
        composable(route = Screen.Home.route) { HomeScreen(navController = navController) }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {

        }
        composable(route = Screen.Search.route) { SearchScreen(navController = navController) }
    }
}
