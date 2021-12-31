package com.bhardwaj.pokemon.navigation

import com.bhardwaj.pokemon.utils.Constants.DETAILS_ARGUMENT_KEY
import com.bhardwaj.pokemon.utils.Constants.ROUTE_DETAILS_SCREEN
import com.bhardwaj.pokemon.utils.Constants.ROUTE_HOME_SCREEN
import com.bhardwaj.pokemon.utils.Constants.ROUTE_SEARCH_SCREEN
import com.bhardwaj.pokemon.utils.Constants.ROUTE_SPLASH_SCREEN
import com.bhardwaj.pokemon.utils.Constants.ROUTE_WELCOME_SCREEN

sealed class Screen(val route: String) {
    object Splash : Screen(ROUTE_SPLASH_SCREEN)
    object Welcome : Screen(ROUTE_WELCOME_SCREEN)
    object Home : Screen(ROUTE_HOME_SCREEN)
    object Search : Screen(ROUTE_SEARCH_SCREEN)
    object Details : Screen("$ROUTE_DETAILS_SCREEN/{$DETAILS_ARGUMENT_KEY}") {
        fun passHeroId(heroId: Int): String {
            return "$ROUTE_DETAILS_SCREEN/$heroId"
        }
    }
}
