package com.disheecompose.ui

import com.disheecompose.models.Restaurant


data class RestaurantUiState(
    val id: Int = 0,
    val imageResId: Int = 0,
    val title: Int = 0,
    val distance: Int = 0
)

fun RestaurantUiState.toRestaurant(): Restaurant = Restaurant(
    id = id,
    imageResId = imageResId,
    title = title,
    distance = distance
)

fun Restaurant.toRestaurantUiState():RestaurantUiState = RestaurantUiState(
    id = id,
    imageResId = imageResId,
    title = title,
    distance = distance
)
