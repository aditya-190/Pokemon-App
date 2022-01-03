package com.bhardwaj.pokemon.presentation.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bhardwaj.pokemon.R
import com.bhardwaj.pokemon.ui.theme.EXTRA_SMALL_PADDING
import com.bhardwaj.pokemon.ui.theme.LightGray
import com.bhardwaj.pokemon.ui.theme.StarColor

enum class StarType {
    FILLED,
    HALF,
    EMPTY
}

@Composable
fun RatingWidget(
    modifier: Modifier,
    rating: Double,
    scaleFactor: Float = 2F,
    spaceBetween: Dp = EXTRA_SMALL_PADDING
) {
    val starPathString = stringResource(R.string.star_path)
    val starPath = remember { PathParser().parsePathString(pathData = starPathString).toPath() }
    val starPathBounds = remember { starPath.getBounds() }

    val maxStars by remember { mutableStateOf(5) }
    var filledStars by remember { mutableStateOf(0) }
    var halfFilledStars by remember { mutableStateOf(0) }
    var emptyStars by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = rating) {
        val (firstNumber, secondNumber) = rating.toString().split(".").map { it.toInt() }

        if (firstNumber in 0..5 && secondNumber in 0..9) {
            filledStars = firstNumber
            if (secondNumber in 1..5) {
                halfFilledStars++
            }
            if (secondNumber in 6..9) {
                filledStars++
            }

            if (firstNumber == 5 && secondNumber > 0) {
                emptyStars = 5
                filledStars = 0
                halfFilledStars = 0
            }
        } else {
            Log.d("Aditya", "CalculateStar: Invalid Rating Number")
        }
    }

    emptyStars = maxStars - (filledStars + halfFilledStars)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        repeat(filledStars) {
            Star(
                starPath = starPath,
                starPathBounds = starPathBounds,
                scaleFactor = scaleFactor,
                type = StarType.FILLED
            )
        }

        repeat(halfFilledStars) {
            Star(
                starPath = starPath,
                starPathBounds = starPathBounds,
                scaleFactor = scaleFactor,
                type = StarType.HALF
            )
        }

        repeat(emptyStars) {
            Star(
                starPath = starPath,
                starPathBounds = starPathBounds,
                scaleFactor = scaleFactor,
                type = StarType.EMPTY
            )
        }
    }
}

@Composable
fun Star(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float,
    type: StarType,
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = size
        scale(scale = scaleFactor) {
            translate(
                left = (canvasSize.width - starPathBounds.width) / 2F,
                top = (canvasSize.height - starPathBounds.height) / 2F
            ) {
                drawPath(
                    path = starPath,
                    color = if (type == StarType.FILLED) StarColor else LightGray.copy(0.5F),
                )

                if (type == StarType.HALF) {
                    clipPath(path = starPath) {
                        drawRect(
                            color = StarColor,
                            size = Size(
                                width = starPathBounds.maxDimension / 2F,
                                height = starPathBounds.maxDimension * scaleFactor
                            )
                        )
                    }
                }
            }
        }
    }
}