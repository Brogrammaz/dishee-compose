package com.disheecompose.ui

import com.disheecompose.models.Recipe
import com.disheecompose.models.Curator


data class CuratorUiState(
    val id: Int = 0,
    val imageResId: Int = 0,
    val title: Int = 0,
    val menus: List<Recipe> = listOf()
)

fun CuratorUiState.toCurator(): Curator = Curator(
    id = id,
    imageResId = imageResId,
    title = title,
    menuList = menus
)

fun Curator.toCuratorUiState():CuratorUiState = CuratorUiState(
    id = id,
    imageResId = imageResId,
    title = title,
    menus = menuList
)
