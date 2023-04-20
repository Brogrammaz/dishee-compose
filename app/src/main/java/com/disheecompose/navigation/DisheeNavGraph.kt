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
import com.disheecompose.ui.auth.*
import com.disheecompose.ui.home.AddRecipeDestination
import com.disheecompose.ui.home.AddRecipeScreen
import com.disheecompose.ui.home.HomeDestination
import com.disheecompose.ui.home.HomeScreen
import kotlinx.coroutines.delay

enum class DisheeScreen {
    Welcome,
    Register,
    UploadPicture,
    Success
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
                        route = LoginDestination.route,
                        navOptions = NavOptions.Builder()
                            .setPopUpTo(DisheeScreen.Welcome.name, true)
                            .build()
                    )
                }
            }


            composable(route = DisheeScreen.Register.name){
                RegisterScreen(
                    onRegisterButtonClicked = { navController.navigate(DisheeScreen.UploadPicture.name) },
                    onLoginTextButtonClicked = { navController.navigate(LoginDestination.route) }
                )
            }

            composable(route = DisheeScreen.UploadPicture.name){
                UploadPictureScreen(
                    onNextButtonClicked = { navController.navigate(DisheeScreen.Success.name) }
                )
            }

            composable(route = DisheeScreen.Success.name){
                SuccessScreen(
                    onGetStartedButtonClicked = { navController.navigate(LoginDestination.route) }
                )
            }

            composable(route = LoginDestination.route){
                LoginScreen(
                    onSignupButtonClicked = { navController.navigate(HomeDestination.route)},
                    onRegisterTextButtonClicked = {navController.navigate(DisheeScreen.Register.name)}
                )
            }

            composable(route = HomeDestination.route){
                HomeScreen(
                    onCuratorOnClick = {
                        navController.navigate("${CuratorScreenDestination.route}/${it}")
                    },
                    onRecipeOnClick = {
                        navController.navigate("${RecipeScreenDestination.route}/${it}")
                    },
                    addRecipeClick = {
                        navController.navigate(AddRecipeDestination.route)
                    }
                )
            }

            composable(
                route = CuratorScreenDestination.routeWithArgs,
                arguments = listOf(navArgument(CuratorScreenDestination.curatorIdArg){
                    type = NavType.IntType
                })
            ){
                CuratorScreen(
                    onRecipeClick = { navController.navigate("${RecipeScreenDestination.route}/${it}")},
                    navigateBack = {navController.navigate(HomeDestination.route)}
                )
            }

            composable(
                route = RecipeScreenDestination.routeWithArgs,
                arguments = listOf(navArgument(RecipeScreenDestination.recipeIdArg){
                    type = NavType.IntType
                })
            ){
                RecipeScreen(
                    onSubmitReply = {}
                )
            }

            composable(
                route = AddRecipeDestination.route
            ){
                AddRecipeScreen(
                    navigateBack = { navController.navigate(HomeDestination.route) }
                )
            }
        }
    }

}