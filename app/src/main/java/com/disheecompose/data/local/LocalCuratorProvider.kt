package com.disheecompose.data.local

import com.disheecompose.R
import com.disheecompose.models.Curator

object LocalCuratorProvider {
    val recipe = LocalRecipeProvider

    val karuchiu: Curator = Curator(
            0,
            "karuchiu@gmail.com",
                R.drawable.benz_dp,
            "Karuchiu",
            "Don",
            "12345678",
                listOf(recipe.fufu, recipe.veggies, recipe.pilau)
        )


    val njogu: Curator = Curator(
        1,
        "njogu@gmail.com",
        R.drawable.tabata,
        "Dan",
        "Njogu",
        "njogu123",
          listOf(recipe.veggies)
    )


    val cobi: Curator = Curator(
        2,
        "cobi@gmail.com",
        R.drawable.karibu_kanairo,
        "Mbutu",
        "Don",
        "trippl3m",
        listOf(recipe.fufu, recipe.veggies)
    )

    val east: Curator = Curator(
        3,
        "east@gmail.com",
        R.drawable.iten,
        "Lofty",
        "Rugby",
        "east1234",
        listOf()
    )

    val curatorList = listOf(
        karuchiu,njogu,cobi, east
    )


}

