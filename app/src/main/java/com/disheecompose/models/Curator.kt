package com.disheecompose.models

import androidx.annotation.DrawableRes

data class Curator(
    val curatorId: Int,
    val email: String,
    @DrawableRes val curatorImage: Int,
    val firstName: String,
    val lastName: String,
    val password: String,
    val recipeList: List<Recipe>?
)
