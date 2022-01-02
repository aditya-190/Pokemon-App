package com.bhardwaj.pokemon.domain.modal

import androidx.annotation.DrawableRes
import com.bhardwaj.pokemon.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String,
) {
    object First : OnBoardingPage(
        image = R.drawable.icon_welcome,
        title = "Greetings",
        description = "Are you a Boruto fan? Because if you are then we have a great news for you!"
    )

    object Second : OnBoardingPage(
        image = R.drawable.icon_explore,
        title = "Explore",
        description = "Find your favorite heroes and learn some of the things that you didn't know about."
    )

    object Third : OnBoardingPage(
        image = R.drawable.icon_power,
        title = "Power",
        description = "Check out your hero's power and  see how much are they strong comparing to others."
    )
}
