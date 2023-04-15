package com.disheecompose.data.local

import com.disheecompose.R
import com.disheecompose.models.Curator

object LocalCuratorProvider {

    val curatorList = mutableListOf(
        Curator(
            id = 0,
            imageResId = R.drawable.healthy_food,
            title = R.string.healthy_food,
            menuList = listOf(
                LocalRecipeProvider.recipeList[0],
                LocalRecipeProvider.recipeList[1]
            )
        ),
        Curator(
            id = 1,
            imageResId = R.drawable.vegan_resto,
            title = R.string.vegan_resto,
            menuList = mutableListOf(
                LocalRecipeProvider.recipeList[2],
                LocalRecipeProvider.recipeList[3],
                LocalRecipeProvider.recipeList[3]
            )
        )
    )
}