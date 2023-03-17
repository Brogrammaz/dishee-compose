package com.disheecompose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.disheecompose.ui.HomeScreen
import com.disheecompose.ui.LoginScreen
import com.disheecompose.ui.components.BottomNavigation

enum class DisheeScreen {
    Login,
    Home
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisheeApp(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {}
    ) {
        NavHost(
            navController = navController ,
            startDestination = DisheeScreen.Login.name,
            modifier = modifier.padding(16.dp)
        ){
            composable(route = DisheeScreen.Login.name){
                LoginScreen(
                    onSignupButtonClicked = { navController.navigate(DisheeScreen.Home.name) },
                    onRegisterTextButtonClicked = {}
                )
            }
            composable(route = DisheeScreen.Home.name){
                HomeScreen()
            }
        }
    }
}