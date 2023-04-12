package com.disheecompose.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.disheecompose.ui.*
import com.disheecompose.ui.home.HomeDestination
import com.disheecompose.ui.home.HomeScreen
import kotlinx.coroutines.delay

enum class DisheeScreen {
    Welcome,
    Register,
    UploadPicture,
    Success,
    Login,
    PostDetail,
    OrderDetail,
    Cart,
    Payment
}

/**
 * Provides Navigation graph for the application.
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DisheeNavHost(
    modifier: Modifier = Modifier
){
    val navController = rememberNavController()

    Scaffold (
        topBar = {}
    ){
        NavHost(
            navController = navController ,
            startDestination = DisheeScreen.Welcome.name,
            modifier = modifier
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
                    onSignupButtonClicked = { navController.navigate(HomeDestination.route) },
                    onRegisterTextButtonClicked = {navController.navigate(DisheeScreen.Register.name)}
                )
            }

            composable(route = HomeDestination.route){
                HomeScreen(
                    onRestaurantOnClick = {
                        navController.navigate("${PostDetailDestination.route}/${it}")
                                          },
                    onSpecialDealOnClick = { navController.navigate(DisheeScreen.OrderDetail.name) },
                    onCartScreenNavigation = {navController.navigate(DisheeScreen.Cart.name)}
                )
            }

            composable(
                route = PostDetailDestination.routeWithArgs,
                arguments = listOf(navArgument(PostDetailDestination.restaurantIdArg){
                    type = NavType.IntType
                })
            ){
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