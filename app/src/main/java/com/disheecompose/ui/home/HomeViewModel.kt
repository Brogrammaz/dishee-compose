package com.disheecompose.ui.home

import androidx.lifecycle.ViewModel
import com.disheecompose.data.local.LocalRestaurantProvider
import com.disheecompose.models.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        initializeUiState()
    }

    private fun initializeUiState() {
        val restaurantList: List<Restaurant> =
            LocalRestaurantProvider.restaurantList
        _uiState.value =
            HomeUiState(restaurantList = restaurantList)
    }
}

data class HomeUiState(val restaurantList: List<Restaurant> = listOf())