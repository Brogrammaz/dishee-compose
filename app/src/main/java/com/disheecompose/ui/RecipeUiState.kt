package com.disheecompose.ui

import com.disheecompose.models.Comment

data class RecipeUiState(
    val id: Int = 0,
    val imageResId: Int = 0,
    val title: String = "",
    val comments: List<Comment>? = listOf()
)
