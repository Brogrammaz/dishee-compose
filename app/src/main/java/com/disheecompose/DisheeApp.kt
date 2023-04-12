package com.disheecompose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.disheecompose.navigation.DisheeNavHost

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DisheeApp() {
    DisheeNavHost()
}