package com.disheecompose.ui

import com.disheecompose.models.Recipe
import com.disheecompose.models.Curator

// TODO Remove password
data class CuratorUiState(
    val id: Int = 0,
    val imageResId: Int = 0,
    val title: String = "",
    val recipes: List<Recipe>? = listOf(),
    val email: String = ""
)

fun CuratorUiState.toCurator(): Curator {
    val nameParts = title.split(" ")
    val firstName = nameParts.firstOrNull() ?: ""
    val lastName = nameParts.drop(1).joinToString(" ")
    return Curator(
        curatorId = id,
        curatorImage = imageResId,
        recipeList = recipes,
        email = email,
        firstName = firstName,
        lastName = lastName,
        password = ""
    )
}


fun Curator.toCuratorUiState():CuratorUiState = CuratorUiState(
    id = curatorId,
    imageResId = curatorImage,
    recipes = recipeList,
    email = email,
    title = firstName + lastName
)
