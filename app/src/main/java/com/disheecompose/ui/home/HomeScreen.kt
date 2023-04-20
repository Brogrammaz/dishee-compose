package com.disheecompose.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.disheecompose.DisheyTopAppBar
import com.disheecompose.R
import com.disheecompose.models.Curator
import com.disheecompose.models.Recipe
import com.disheecompose.navigation.NavigationDestination
import com.disheecompose.ui.RecipeRow

object HomeDestination: NavigationDestination{
    override val route = "home"
    override val titleRes: Int = R.string.dishee
}

/*TODO
*  Prevent BackStackEntry to Login page or register page
* */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onCuratorOnClick: (Int) -> Unit ,
    onRecipeOnClick: (Int) -> Unit,
    addRecipeClick: () -> Unit,
    viewModel: HomeViewModel = viewModel()
){
    val homeUiState by viewModel.uiState.collectAsState()

    val focusManager = LocalFocusManager.current

    var recipeSearch by remember{
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            DisheyTopAppBar(
                title = stringResource(id = HomeDestination.titleRes),
                canNavigateBack = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = addRecipeClick) {
                Icon(Icons.Filled.Add, contentDescription = "Add Recipe")
            }
        }
    ) {
        Column(
            /*modifier = modifier
                .padding(top = 32.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),*/
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column{
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.find_recipe),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        Icons.Outlined.Notifications,
                        contentDescription = null,
                        modifier = modifier
                            .size(40.dp)
                            .align(Alignment.CenterVertically)
                    )
                }

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                ){
                    OutlinedTextField(
                        value = recipeSearch,
                        onValueChange = {recipeSearch = it},
                        label = {
                            Text(
                                text = stringResource(id = R.string.what_recipe),
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {focusManager.clearFocus()}
                        ),
                        leadingIcon = {
                            Icon(Icons.Outlined.Search, contentDescription = null)
                        }
                    )
                    Spacer(modifier = modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.filter_icon),
                        contentDescription = null,
                        modifier = modifier
                            .size(40.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }

            PopularCurators(
                curatorList = homeUiState.curatorList,
                onCuratorOnClick = { onCuratorOnClick(it.curatorId) }
            )

            PopularRecipes(
                recipeList = homeUiState.popularRecipeList,
                onRecipeOnClick = {onRecipeOnClick(it.recipeId)}
            )
        }
    }


}

@Composable
fun PopularCurators(
    curatorList: List<Curator>,
    modifier: Modifier = Modifier,
    onCuratorOnClick: (Curator) -> Unit = {}
){
    Column {
        Row(
            modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.top_curators),
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = modifier.weight(1f))
        }

        CuratorRow(
            curators = curatorList,
            curatorOnClick = onCuratorOnClick
        )
    }
}

@Composable
fun CuratorCard(
    curator: Curator,
    modifier: Modifier = Modifier,
    curatorOnClick: (Curator) -> Unit
){
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .size(180.dp)
            .clickable { curatorOnClick(curator) }
    ) {
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                modifier = modifier.size(80.dp),
                painter = painterResource(id = curator.curatorImage),
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.full_name, curator.firstName, curator.lastName),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
fun CuratorRow(
    curators: List<Curator>,
    curatorOnClick: (Curator) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(curators.size) { index ->
            CuratorCard(
                curator = curators[index],
                curatorOnClick = curatorOnClick
            )
        }
    }
}

@Composable
fun PopularRecipes(
    recipeList: List<Recipe>,
    modifier: Modifier = Modifier,
    onRecipeOnClick: (Recipe) -> Unit = {}
){
    Column {
        Row(
            modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.top_recipes),
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = modifier.weight(1f))
        }

        RecipeRow(
            recipes = recipeList,
            onRecipeOnClick = onRecipeOnClick
        )

    }
}

@Composable
fun RecipeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    recipeOnClick: (Recipe) -> Unit
){
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .size(180.dp)
            .clickable { recipeOnClick(recipe) },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                modifier = modifier.size(80.dp),
                painter = painterResource(id = recipe.recipeImage),
                contentDescription = null
            )
            Text(
                text =  recipe.recipeName,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

/*
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun Appreview(){
    DisheecomposeTheme {
        HomeScreen()
    }
}*/
