package com.disheecompose.ui

import com.disheecompose.models.Menu
import com.disheecompose.models.Restaurant


data class RestaurantUiState(
    val id: Int = 0,
    val imageResId: Int = 0,
    val title: Int = 0,
    val distance: Int = 0,
    val menus: List<Menu> = listOf()
)

fun RestaurantUiState.toRestaurant(): Restaurant = Restaurant(
    id = id,
    imageResId = imageResId,
    title = title,
    distance = distance,
    menuList = menus
)

fun Restaurant.toRestaurantUiState():RestaurantUiState = RestaurantUiState(
    id = id,
    imageResId = imageResId,
    title = title,
    distance = distance,
    menus = menuList
)
