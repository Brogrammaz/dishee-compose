package com.disheecompose.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.disheecompose.data.local.LocalRestaurantProvider
import com.disheecompose.models.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PostDetailsViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _uiState = MutableStateFlow(RestaurantUiState())
    val uiState: StateFlow<RestaurantUiState> = _uiState

    private val restaurantId: Int = checkNotNull(savedStateHandle["restaurantId"])

    init {
        initializeUIState()
    }

    private fun initializeUIState() {
        var restaurant: Restaurant = LocalRestaurantProvider.restaurantList[restaurantId]

        _uiState.value = RestaurantUiState(
            id = restaurant.id,
            imageResId = restaurant.imageResId,
            title = restaurant.title,
            distance = restaurant.distance
        )
    }
}