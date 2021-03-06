package com.bhardwaj.pokemon.presentation.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.bhardwaj.pokemon.R
import com.bhardwaj.pokemon.domain.modal.Hero
import com.bhardwaj.pokemon.ui.theme.DarkGray
import com.bhardwaj.pokemon.ui.theme.LightGray
import com.bhardwaj.pokemon.ui.theme.NETWORK_ERROR_ICON_SIZE
import com.bhardwaj.pokemon.ui.theme.SMALL_PADDING
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    heroes: LazyPagingItems<Hero>? = null
) {
    var message by remember { mutableStateOf("Find your Favorite Hero!") }
    var icon by remember { mutableStateOf(R.drawable.icon_favourites) }

    if (error != null) {
        message = parseErrorMessage(errorMessage = error)
        icon = R.drawable.icon_network_error
    }

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0F,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    LaunchedEffect(key1 = true) { startAnimation = true }

    EmptyContent(
        alphaAnimation = alphaAnimation,
        icon = icon,
        message = message,
        error = error,
        heroes = heroes,
    )
}

@Composable
fun EmptyContent(
    alphaAnimation: Float,
    icon: Int,
    message: String,
    error: LoadState.Error? = null,
    heroes: LazyPagingItems<Hero>? = null
) {

    var isRefreshing by remember { mutableStateOf(false) }

    SwipeRefresh(
        swipeEnabled = error != null,
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            isRefreshing = true
            heroes?.refresh()
            isRefreshing = false
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Icon(
                modifier = Modifier
                    .alpha(alpha = alphaAnimation)
                    .size(NETWORK_ERROR_ICON_SIZE),
                painter = painterResource(id = icon),
                contentDescription = stringResource(R.string.network_error_icon),
                tint = if (isSystemInDarkTheme()) LightGray else DarkGray
            )

            Text(
                modifier = Modifier
                    .alpha(alpha = alphaAnimation)
                    .padding(top = SMALL_PADDING),
                text = message,
                color = if (isSystemInDarkTheme()) LightGray else DarkGray,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )
        }
    }
}

fun parseErrorMessage(errorMessage: LoadState.Error): String {
    return when (errorMessage.error) {
        is SocketTimeoutException -> {
            "Server Unavailable."
        }
        is ConnectException -> {
            "Internet Unavailable."
        }
        else -> {
            "Unknown Error."
        }
    }
}