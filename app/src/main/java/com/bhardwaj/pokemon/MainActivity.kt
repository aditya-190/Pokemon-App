package com.bhardwaj.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.bhardwaj.pokemon.ui.theme.PokemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonTheme {
                Text(
                    "Aditya Bhardwaj",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}