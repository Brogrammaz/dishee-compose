package com.disheecompose.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.disheecompose.data.local.LocalCuratorProvider
import com.disheecompose.models.Curator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CuratorViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _uiState = MutableStateFlow(CuratorUiState())
    val uiState: StateFlow<CuratorUiState> = _uiState

    private val curatorId: Int = checkNotNull(savedStateHandle[CuratorScreenDestination.curatorIdArg])

    init {
        initializeUIState()
    }

    private fun initializeUIState() {
        var curator: Curator = LocalCuratorProvider.curatorList[curatorId]

        _uiState.value = CuratorUiState(
            id = curator.curatorId,
            imageResId = curator.curatorImage,
            title =  curator.firstName +" " + curator.lastName,
            recipes = curator.recipeList
        )
    }
}