package com.bhardwaj.pokemon.utils

object Constants {
    const val BASE_URL = "https://pokemon-server-api.herokuapp.com"
//    const val BASE_URL = "http://10.0.2.2:8080"

    const val ROUTE_SPLASH_SCREEN = "route_splash_screen"
    const val ROUTE_WELCOME_SCREEN = "route_welcome_screen"
    const val ROUTE_HOME_SCREEN = "route_home_screen"
    const val ROUTE_SEARCH_SCREEN = "route_search_screen"
    const val ROUTE_DETAILS_SCREEN = "details_screen"

    const val DETAILS_ARGUMENT_KEY = "heroId"

    const val PREFERENCES_NAME = "pokemon_preferences"
    const val PREFERENCES_KEY = "on_boarding_completed"

    const val POKEMON_DATABASE = "pokemon_database"
    const val HERO_DATABASE_TABLE = "hero_table"
    const val HERO_REMOTE_KEY_DATABASE_TABLE = "hero_remote_key_table"

    const val HEROES_PER_PAGES_GET_HEROES = 6
    const val PAGES_PER_REQUEST_GET_HEROES = 1

    const val HEROES_PER_PAGES_SEARCH_HEROES = 6
    const val PAGES_PER_REQUEST_SEARCH_HEROES = 1
}