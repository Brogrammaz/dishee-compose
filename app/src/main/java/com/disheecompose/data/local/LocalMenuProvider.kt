package com.disheecompose.data.local

import com.disheecompose.R
import com.disheecompose.models.Menu

object LocalMenuProvider {
    val menuList = mutableListOf(
        Menu(
            R.drawable.healthy_food,
            "Special 1",
            "Ksh 750"
        ),
        Menu(
            R.drawable.healthy_food,
            "Special 2",
            "Ksh 500"
        ),
        Menu(
            R.drawable.vegan_resto,
            "Special 2",
            "Ksh 370"
        ),
        Menu(
            R.drawable.vegan_resto,
            "Special 2",
            "Ksh 1000"
        )
    )
}