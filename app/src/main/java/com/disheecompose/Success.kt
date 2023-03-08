package com.disheecompose

import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.disheecompose.ui.theme.*

class Success : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisheecomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Composable
fun GradientButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    gradient: Brush,
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        onClick = { onClick() }
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .padding(16.dp, 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text, color = textColor)
        }
    }
}

@Preview(
    name = "Gradient Button Preview",
    showBackground = true
)
@Composable
fun GradientButtonPreview() {
    DisheecomposeTheme {
        GradientButton(
            modifier = Modifier.fillMaxWidth(),
            "Android",
            Color.White,
            Brush.horizontalGradient(
                colors = listOf(
                    IconGreen,
                    ThemeGreen,
                    ThemeGreen
                )
            ),
            onClick = { }
        )
    }
}

@Composable
fun CongratsText(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.background(White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.congrats),
            color = Green8B,
            fontSize = 35.sp
        )
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = stringResource(id = R.string.ready_profile),
            color = TextBlack,
            fontSize = 30.sp
        )
    }
}

@Preview(
    name = "Congrats Preview"
)
@Composable
fun CongratsPreview() {
    DisheecomposeTheme {
        CongratsText()
    }
}

@Composable
fun SuccessHeader(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter

    ) {
        Image(
            painter = painterResource(id = R.drawable.pattern),
            contentDescription = "Success Background",
            modifier = modifier.background(MaterialTheme.colorScheme.background)
                .scale(2.70f,1.4f)
                .fillMaxSize()

        )
        Box(modifier = modifier) {
            Image(
                painter = painterResource(id = R.drawable.congratulations),
                contentDescription = "Success Image",
                modifier = modifier
            )
        }
    }

}

@Preview
@Composable
fun SuccessHeaderPreview() {
    DisheecomposeTheme {
        SuccessHeader()
    }
}

@Composable
fun SuccessScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.background(
            MaterialTheme.colorScheme.background
        )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SuccessHeader(modifier.height(150.dp))
        Spacer(modifier = modifier.weight(0.4f))
        CongratsText()
        Spacer(modifier = modifier.weight(1f))
        GradientButton(
            text = stringResource(id = R.string.get_started),
            textColor = White, gradient = Brush.linearGradient(
                colors = listOf(
                    IconGreen,
                    ThemeGreen,
                    ThemeGreen
                )
            )) {

        }
    }
}

@Preview(

)
@Composable
fun SuccessScreenPreview() {

    DisheecomposeTheme {
        SuccessScreen()
    }

}