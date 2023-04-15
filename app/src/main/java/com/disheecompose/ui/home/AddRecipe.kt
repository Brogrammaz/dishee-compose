package com.disheecompose.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.disheecompose.DisheeTopAppBar
import com.disheecompose.R
import com.disheecompose.navigation.NavigationDestination
import com.disheecompose.ui.components.OutlinedTextAreaSample
import com.disheecompose.ui.components.OutlinedTextFieldSample

object AddRecipeDestination: NavigationDestination {
    override val route = "add_recipe"
    override val titleRes: Int = R.string.add_recipe
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeScreen(
    navigateBack: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    var recipeName by remember{
        mutableStateOf("")
    }
    
    var ingredients by remember{
        mutableStateOf("")
    }

    var directions by remember{
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            DisheeTopAppBar(
                title = stringResource(id = AddRecipeDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextFieldSample(
                value = recipeName,
                onValueChange = {recipeName = it},
                label = R.string.recipe_name,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {focusManager.moveFocus(FocusDirection.Down)}
                )
            )

            OutlinedTextAreaSample(
                value = ingredients,
                onValueChange = {ingredients = it},
                label = R.string.ingredients,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {focusManager.moveFocus(FocusDirection.Down)}
                ),
                maxLines = 5
            )

            OutlinedTextAreaSample(
                value = directions,
                onValueChange = {directions = it},
                label = R.string.directions,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onNext = {focusManager.clearFocus()}
                ),
                maxLines = 7
            )
            
            UploadRecipeImage()
            
            Row() {
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = { /*TODO*/ }) {
                    Text(text = stringResource(id = R.string.upload))
                }
            }
        }
    }
}

@Composable
fun UploadRecipeImage() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = R.string.upload_image))
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.profilepic),
            contentDescription = stringResource(id = R.string.upload_image),
            modifier = Modifier
                .size(200.dp)
                .clickable { /*TODO open phone's gallery*/ }
        )
    }
}