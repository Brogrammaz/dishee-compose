package com.disheecompose.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.disheecompose.AppViewModelProvider
import com.disheecompose.R
import com.disheecompose.models.Comment
import com.disheecompose.navigation.NavigationDestination
import com.disheecompose.ui.theme.DisheeComposeTheme2
import java.time.Instant
import java.util.*

object RecipeScreenDestination: NavigationDestination {
    override val route = "recipe_screen"
    override val titleRes: Int = R.string.this_recipe
    const val recipeIdArg = "recipeId"
    val routeWithArgs = "$route/{$recipeIdArg}"
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeScreen(
    viewModel: RecipeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = AppViewModelProvider.Factory),
    onSubmitReply: (String) -> Unit
){
    val focusManager = LocalFocusManager.current
    var replyText by remember { mutableStateOf("") }

    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val recipeUiState = viewModel.uiState.collectAsState()

    DisheeComposeTheme2 {
        Box(modifier = Modifier.fillMaxSize()) {
            BackdropScaffold(
                peekHeight = 0.dp,
                headerHeight = 0.dp,
                scaffoldState = scaffoldState,
                appBar = { },
                backLayerContent = {
                    BoxWithConstraints(
                        modifier = Modifier
                            .fillMaxHeight(0.35f)
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.medium)
                    ) {
                        val height = if (maxHeight > screenHeight / 2) screenHeight / 2 else maxHeight
                        Image(
                            painter = painterResource(recipeUiState.value.imageResId),
                            contentDescription = null,
                            modifier = Modifier
                                .height(height)
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    }
                },
                frontLayerContent = {
                    BoxWithConstraints(
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        val constraints = maxHeight / 2
                        val height = if (constraints > screenHeight / 2) screenHeight / 2 else constraints
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .height(height),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text =  recipeUiState.value.title,
                                    style = MaterialTheme.typography.titleSmall,
                                    maxLines = 2
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Icon(Icons.Filled.Favorite, contentDescription =null)
                            }
                            IngredientsArea()
                            DirectionsArea()
                            CommentSection(
                                Comment(R.drawable.profilepic, "Njogu", "Whack"),
                                Comment(R.drawable.profilepic, "Karuchiu", "Comida Interesante"),
                                Comment(R.drawable.profilepic, "Njogu", "Whack"),
                                Comment(R.drawable.profilepic, "Karuchiu", "Comida Interesante"),
                                Comment(R.drawable.profilepic, "Njogu", "Whack"),
                                Comment(R.drawable.profilepic, "Karuchiu", "Comida Interesante"),
                                Comment(R.drawable.profilepic, "Njogu", "Whack"),
                                Comment(R.drawable.profilepic, "Karuchiu", "Comida Interesante"),
                                reply = replyText,
                                onValueChange = {replyText = it},
                                label = R.string.write_comment,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext = {focusManager.clearFocus()}
                                ),
                                onSubmitReply = {onSubmitReply(replyText)}
                            )
                        }
                    }
                },
            )

        }
    }
}

@Composable
fun IngredientsArea() {
    Column {
        Text(
            text = stringResource(id = R.string.ingredients),
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = stringResource(id = R.string.ingredient_example),
            style = MaterialTheme.typography.bodySmall,
            softWrap = true,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun DirectionsArea() {
    Column {
        Text(
            text = stringResource(id = R.string.directions),
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = stringResource(id = R.string.directions_example),
            style = MaterialTheme.typography.bodySmall,
            softWrap = true,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CommentSection(
    vararg comments: Comment,
    reply: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    onSubmitReply: (String) -> Unit
) {
    Text(
        text = stringResource(id = R.string.comments),
        style = MaterialTheme.typography.labelLarge
    )
    ReplyTextField(
        reply = reply,
        onValueChange = onValueChange,
        label = label,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        onSubmitReply = onSubmitReply
    )
    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        items(comments.size){ index ->
            CommentCard(
                comment = Comment(
                    comments[index].userImage,
                    comments[index].userName,
                    comments[index].comment
                )
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CommentCard(
    comment: Comment,
    modifier: Modifier = Modifier
){
    androidx.compose.material3.Card {
        Row {
            androidx.compose.material3.Icon(
                painter = painterResource(id = comment.userImage),
                contentDescription = null
            )
            Column(
                modifier.padding(8.dp)
            ) {
                androidx.compose.material3.Text(
                    text = comment.userName,
                    style = MaterialTheme.typography.labelLarge
                )
                androidx.compose.material3.Text(
                    text = Date.from(Instant.now()).toString(),
                    style = MaterialTheme.typography.labelSmall
                )

                androidx.compose.material3.Text(
                    text = comment.comment,
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 4
                )
            }
        }
    }
}

@Composable
fun ReplyTextField(
    reply: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    onSubmitReply: (String) -> Unit
) {
    Column {
        TextField(
            value = reply,
            onValueChange = onValueChange,
            label = {
                androidx.compose.material3.Text(
                    text = stringResource(id = label),
                    style = MaterialTheme.typography.labelMedium
                )
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 70.dp),
            maxLines = 3
        )

        androidx.compose.material3.Button(
            onClick = {onSubmitReply(reply)},
            modifier = Modifier.align(Alignment.End)
        ) {
            androidx.compose.material3.Text("Submit")
        }
    }
}


/*
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun OrderPreview(){
    RecipeScreen()
}*/
