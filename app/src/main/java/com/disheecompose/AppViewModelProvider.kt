package com.disheecompose

import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.disheecompose.ui.CuratorViewModel
import com.disheecompose.ui.RecipeViewModel
import com.disheecompose.ui.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel()
        }

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