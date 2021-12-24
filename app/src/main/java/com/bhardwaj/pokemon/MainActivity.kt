package com.bhardwaj.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bhardwaj.pokemon.navigation.SetupNavGraph
import com.bhardwaj.pokemon.ui.theme.PokemonTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}