package com.disheecompose.models

data class Curator(
    val id: Int,
    val imageResId: Int,
    val title: Int,
    val menuList: List<Recipe>
)
