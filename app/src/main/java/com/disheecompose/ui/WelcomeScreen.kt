package com.disheecompose.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.disheecompose.R
import com.disheecompose.ui.theme.DisheecomposeTheme
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {
    Column (
        modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Box(
            modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(Color.Blue),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.dishee),
                color = Color.White
            )
        }
        Spacer(modifier = modifier.height(40.dp))
        LoadingDotsAnimation()
    }
}


/**
* Define a loading dots Animation for the welcome splash screen.
* */

@Composable
fun LoadingDotsAnimation(
    modifier: Modifier = Modifier,
    dotSize : Dp = 20.dp,
    dotColor : Color = Color.Green,
    spaceBetween : Dp = 10.dp,
    displacement : Dp = 20.dp
) {

    val dots = listOf(
        remember {
            Animatable(initialValue = 0f)
        },
        remember {
            Animatable(initialValue = 0f)
        },
        remember {
            Animatable(initialValue = 0f)
        }
    )

    val dotValues = dots.map { it.value }
    val distance = with(LocalDensity.current) {displacement.toPx()}
    val lastDot = dotValues.size - 1

    /**
     * The actual animation.
     * */

    dots.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable){
            /**
             * Add a delay so that the animation occurs asynchronously to the dots.
             * Therefore, multiplying the [index] with 100 milliseconds allows the first dot to be displaced first then the second in that order.
             * */
            delay(index * 100L)
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1200
                        0.0f at 0 with LinearOutSlowInEasing
                        1.0f at 300 with LinearOutSlowInEasing
                        /**
                         * The animation starts from 0 and after 300 milliseconds the dot if fully displaced.
                         * The following Keyframes (below) ensure that other dots are fully displaced before the animation restarts.
                         * */
                        0.0f at 600 with LinearOutSlowInEasing
                        0.0f at 1200 with LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {

        dotValues.forEachIndexed{index, item ->
            Box(
                modifier = modifier
                    .size(dotSize)
                    /**
                     * invoke the transformation to enable the animation
                     * */
                    .graphicsLayer {
                        /**
                         * translate the dot upwards using a negative motion (-item)
                         * */
                        translationY = -item * distance
                    }
                    .background(
                        color = dotColor,
                        shape = CircleShape
                    )
            ) 
            
            if (index != lastDot){
                Spacer(modifier = Modifier.width(spaceBetween))
            }
        }

    }

}

@Preview(
    name = "App Name Design Preview",
    showBackground = true
)
@Composable
fun AppNameDesignPreview() {
    WelcomeScreen()
}

@Preview(
    name = "Loading Dot Animation Preview",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoadingDotAnimPreview() {

    DisheecomposeTheme() {
        LoadingDotsAnimation()
    }


}