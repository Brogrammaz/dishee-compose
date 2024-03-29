package com.disheecompose.ui.home

import androidx.lifecycle.ViewModel
import com.disheecompose.data.local.LocalCuratorProvider
import com.disheecompose.data.local.LocalRecipeProvider
import com.disheecompose.models.Curator
import com.disheecompose.models.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState


    init {
        initializeUiState()
    }

    private fun initializeUiState() {
        val recipeList: List<Recipe> =
            LocalRecipeProvider.recipeList
        val curatorList: List<Curator> =
            LocalCuratorProvider.curatorList
        _uiState.value =
            HomeUiState(
                curatorList = curatorList,
                popularRecipeList = recipeList
            )
    }
}

data class HomeUiState(
    val curatorList: List<Curator> = listOf(),
    val popularRecipeList: List<Recipe> = listOf()
)