package com.disheecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.disheecompose.ui.theme.DisheecomposeTheme

class AddItem : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisheecomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting4("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting4(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    DisheecomposeTheme {
        Greeting4("Android")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemOutlinedTextInput(
    modifier: Modifier = Modifier
) {
    var itemName by remember { mutableStateOf("") }

    OutlinedTextField(
        value = itemName,
        onValueChange = { itemName = it},
        label = { Text(text = stringResource(id = R.string.name_title))},
        modifier = modifier.fillMaxWidth().heightIn(70.dp)
    )

}