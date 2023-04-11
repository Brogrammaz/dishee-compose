package com.disheecompose.data.local

import com.disheecompose.R
import com.disheecompose.models.Restaurant

object LocalRestaurantProvider {

    val restaurantList = mutableListOf(
        Restaurant(
            id = 0,
            imageResId = R.drawable.healthy_food,
            distance = R.string.min_12,
            title = R.string.healthy_food,
        ),
        Restaurant(
            id = 1,
            imageResId = R.drawable.vegan_resto,
            distance = R.string.min_8,
            title = R.string.vegan_resto,
        )
    )
}