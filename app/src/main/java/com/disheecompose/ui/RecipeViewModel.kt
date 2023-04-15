package com.disheecompose.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.disheecompose.data.local.LocalRecipeProvider
import com.disheecompose.models.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecipeViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _uiState = MutableStateFlow(RecipeUiState())
    val uiState: StateFlow<RecipeUiState> = _uiState

    private val recipeId: Int = checkNotNull(savedStateHandle[RecipeScreenDestination.recipeIdArg])

    init {
        initializeUIState()
    }

    private fun initializeUIState() {
        var recipe: Recipe = LocalRecipeProvider.recipeList[recipeId]

        _uiState.value = RecipeUiState(
            id = recipe.id,
            imageResId = recipe.imageResId,
            title = recipe.title
        )
    }


}