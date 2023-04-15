package com.disheecompose

import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.disheecompose.ui.CuratorViewModel
import com.disheecompose.ui.RecipeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            CuratorViewModel(
                this.createSavedStateHandle()
            )
        }

        initializer {
            RecipeViewModel(
                this.createSavedStateHandle()
            )
        }
    }
}