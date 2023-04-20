package com.disheecompose.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.disheecompose.AppViewModelProvider
import com.disheecompose.DisheyTopAppBar
import com.disheecompose.R
import com.disheecompose.models.Recipe
import com.disheecompose.navigation.NavigationDestination
import com.disheecompose.ui.home.RecipeCard

object CuratorScreenDestination: NavigationDestination {
    override val route = "curator_screen"
    override val titleRes: Int = R.string.curator_profile
    const val curatorIdArg = "curatorId"
    val routeWithArgs = "$route/{$curatorIdArg}"
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuratorScreen(
    onRecipeClick: (Int) -> Unit = {},
    navigateBack: () -> Unit,
    viewModel: CuratorViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = AppViewModelProvider.Factory)
) {

    val curatorUiState = viewModel.uiState.collectAsState()

    Scaffold (
        topBar = {
            DisheyTopAppBar(
                title = stringResource(id = CuratorScreenDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {

            Column(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth()
                    .padding(40.dp,),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(50))
                        .border(2.dp, color = Color.Cyan, CircleShape),
                    painter = painterResource(id = curatorUiState.value.imageResId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = curatorUiState.value.title,
                    style = MaterialTheme.typography.labelLarge
                )

                Text(
                    text = stringResource(id = R.string.noOfRecipes, curatorUiState.value.recipes!!.size),
                    style = MaterialTheme.typography.titleLarge
                )
            }



            Divider()

            Text(
                text = stringResource(id = R.string.my_recipes),
                style = MaterialTheme.typography.labelMedium
            )

            RecipeRow(
                recipes = curatorUiState.value.recipes!!,
                onRecipeOnClick = { onRecipeClick(it.recipeId) }
            )

        }
    }

    /*
    * TODO
    *  Material Theme
    *  White text on Dark mode not showing
    *
    * */
}

@Composable
fun RecipeRow(
    recipes: List<Recipe>,
    onRecipeOnClick: (Recipe) -> Unit = {}
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(recipes.size) { index ->
            RecipeCard(
                recipe = recipes[index],
                recipeOnClick = onRecipeOnClick
            )
        }
    }
}