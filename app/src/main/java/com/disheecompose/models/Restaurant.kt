package com.disheecompose.models

data class Restaurant(
    val id: Int,
    val imageResId: Int,
    val title: Int,
    val distance: Int,
    val menuList: List<Menu>
)
