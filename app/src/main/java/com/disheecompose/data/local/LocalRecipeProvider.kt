package com.disheecompose.data.local

import com.disheecompose.R
import com.disheecompose.models.Recipe

object LocalRecipeProvider {
    val recipeList = mutableListOf(
        Recipe(
            0,
            R.drawable.healthy_food,
            "Special 1",
        ),
        Recipe(
            1,
            R.drawable.healthy_food,
            "Special 2"
        ),
        Recipe(
            2,
            R.drawable.vegan_resto,
            "Special 2"
        ),
        Recipe(
            3,
            R.drawable.vegan_resto,
            "Special 2"
        )
    )
}