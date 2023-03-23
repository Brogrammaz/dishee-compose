package com.disheecompose

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.disheecompose.ui.*
import kotlinx.coroutines.delay

enum class DisheeScreen {
    Welcome,
    Register,
    UploadPicture,
    Success,
    Login,
    Home,
    PostDetail,
    OrderDetail,
    Cart,
    Payment
}

@RequiresApi(Build.VERSION_CODES.O)
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
            startDestination = DisheeScreen.Welcome.name,
            //modifier = modifier.padding(16.dp)
        ){
            composable(route = DisheeScreen.Welcome.name){
                WelcomeScreen()
                LaunchedEffect(Unit) {
                    delay(2000)
                    navController.navigate(
                        route = DisheeScreen.Login.name,
                        navOptions = NavOptions.Builder()
                            .setPopUpTo(DisheeScreen.Welcome.name, true)
                            .build()
                    )
                }
            }


            composable(route = DisheeScreen.Register.name){
                RegisterScreen(
                    onRegisterButtonClicked = { navController.navigate(DisheeScreen.UploadPicture.name) },
                    onLoginTextButtonClicked = { navController.navigate(DisheeScreen.Login.name) }
                )
            }

            composable(route = DisheeScreen.UploadPicture.name){
                UploadPictureScreen(
                    onNextButtonClicked = { navController.navigate(DisheeScreen.Success.name) }
                )
            }

            composable(route = DisheeScreen.Success.name){
                SuccessScreen(
                    onGetStartedButtonClicked = { navController.navigate(DisheeScreen.Login.name) }
                )
            }

            composable(route = DisheeScreen.Login.name){
                LoginScreen(
                    onSignupButtonClicked = { navController.navigate(DisheeScreen.Home.name) },
                    onRegisterTextButtonClicked = {navController.navigate(DisheeScreen.Register.name)}
                )
            }

            composable(route = DisheeScreen.Home.name){
                HomeScreen(
                    onRestaurantOnClick = {navController.navigate(DisheeScreen.PostDetail.name)},
                    onSpecialDealOnClick = { navController.navigate(DisheeScreen.OrderDetail.name) },
                    onCartScreenNavigation = {navController.navigate(DisheeScreen.Cart.name)}
                )
            }

            composable(route = DisheeScreen.PostDetail.name){
                PostDetailScreen(
                    onOrderClick = { navController.navigate(DisheeScreen.OrderDetail.name)}
                )
            }

            composable(route = DisheeScreen.OrderDetail.name){
                OrderDetailScreen(
                    onAddToCartClick = {navController.navigate(DisheeScreen.Cart.name)}
                )
            }

            composable(route = DisheeScreen.Cart.name){
                CartScreen(
                    onPlaceOrderClick = {navController.navigate(DisheeScreen.Payment.name)},
                    onNavigateBack = {navController.navigate(DisheeScreen.OrderDetail.name)}
                )
            }

            composable(route = DisheeScreen.Payment.name){
                PaymentScreen()
            }
        }
    }
}