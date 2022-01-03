package com.bhardwaj.pokemon.presentation.screens.details

import android.graphics.Color.parseColor
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.BottomSheetValue.Collapsed
import androidx.compose.material.BottomSheetValue.Expanded
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.bhardwaj.pokemon.R
import com.bhardwaj.pokemon.domain.modal.Hero
import com.bhardwaj.pokemon.presentation.components.InfoBox
import com.bhardwaj.pokemon.presentation.components.OrderedList
import com.bhardwaj.pokemon.ui.theme.*
import com.bhardwaj.pokemon.utils.Constants.BASE_URL
import com.bhardwaj.pokemon.utils.Constants.HERO_DESCRIPTION_MAX_LINES
import com.bhardwaj.pokemon.utils.Constants.MINIMUM_BACKGROUND_IMAGE_HEIGHT
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navController: NavController,
    selectedHero: Hero?,
    color: Map<String, String>
) {
    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#FFFFFF") }

    LaunchedEffect(key1 = selectedHero) {
        vibrant = color["vibrant"]!!
        darkVibrant = color["darkVibrant"]!!
        onDarkVibrant = color["onDarkVibrant"]!!
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color(parseColor(darkVibrant))
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = Expanded)
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnimation by animateDpAsState(
        targetValue =
        if (currentSheetFraction == 1F)
            BOTTOM_SHEET_COLLAPSED_RADIUS
        else
            BOTTOM_SHEET_EXPANDED_RADIUS
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnimation,
            topEnd = radiusAnimation
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = BOTTOM_SHEET_MINIMUM_HEIGHT,
        sheetContent = {
            selectedHero?.let {
                BottomSheetContent(
                    selectedHero = it,
                    infoBoxIconColor = Color(parseColor(vibrant)),
                    sheetBackgroundColor = Color(parseColor(darkVibrant)),
                    contentColor = Color(parseColor(onDarkVibrant)),
                )
            }
        },
        content = {
            if (selectedHero != null) {
                BackgroundContent(
                    heroImage = selectedHero.image,
                    imageFraction = currentSheetFraction,
                    backgroundColor = Color(parseColor(darkVibrant)),
                    onCloseClicked = {
                        navController.popBackStack()
                    }
                )
            }
        }
    )
}

@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(DETAILS_LOGO_ICON_SIZE)
                    .weight(2F),
                painter = painterResource(id = R.drawable.icon_logo),
                contentDescription = stringResource(id = R.string.application_logo),
                tint = contentColor
            )

            Text(
                modifier = Modifier.weight(8F),
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(id = R.drawable.icon_power),
                iconColor = infoBoxIconColor,
                title = "${selectedHero.power}",
                subTitle = stringResource(R.string.power),
                textColor = contentColor
            )

            InfoBox(
                icon = painterResource(id = R.drawable.icon_date),
                iconColor = infoBoxIconColor,
                title = selectedHero.month,
                subTitle = stringResource(R.string.month),
                textColor = contentColor
            )

            InfoBox(
                icon = painterResource(id = R.drawable.icon_cake),
                iconColor = infoBoxIconColor,
                title = selectedHero.day,
                subTitle = stringResource(R.string.birthday),
                textColor = contentColor
            )
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.about),
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = selectedHero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = HERO_DESCRIPTION_MAX_LINES
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderedList(
                title = stringResource(R.string.family),
                items = selectedHero.family,
                textColor = contentColor
            )

            OrderedList(
                title = stringResource(R.string.abilities),
                items = selectedHero.abilities,
                textColor = contentColor
            )

            OrderedList(
                title = stringResource(R.string.nature_types),
                items = selectedHero.natureTypes,
                textColor = contentColor
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
fun BackgroundContent(
    heroImage: String,
    imageFraction: Float = 1F,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit
) {

    val imageUrl = "$BASE_URL${heroImage}"
    val painter = rememberImagePainter(imageUrl) {
        error(R.drawable.icon_placholder_image)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + MINIMUM_BACKGROUND_IMAGE_HEIGHT)
                .defaultMinSize()
                .align(Alignment.TopStart),
            painter = painter,
            contentDescription = stringResource(id = R.string.hero_image),
            contentScale = ContentScale.Crop
        )

        IconButton(
            modifier = Modifier
                .padding(all = SMALL_PADDING)
                .align(Alignment.TopEnd),
            onClick = { onCloseClicked() }
        ) {
            Icon(
                modifier = Modifier.size(DETAILS_CLOSE_ICON_SIZE),
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = R.string.close_icon),
                tint = Color.White
            )
        }
    }
}

@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == Collapsed && targetValue == Collapsed -> 1F
            currentValue == Expanded && targetValue == Expanded -> 0F
            currentValue == Collapsed && targetValue == Expanded -> 1F - fraction
            currentValue == Expanded && targetValue == Collapsed -> 0F + fraction
            else -> fraction
        }
    }