package com.disheecompose

import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.disheecompose.ui.PostDetailsViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            PostDetailsViewModel(
                this.createSavedStateHandle()
            )
        }
    }
}