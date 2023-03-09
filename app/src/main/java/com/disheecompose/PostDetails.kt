package com.disheecompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.disheecompose.data.BottomNavItem
import com.disheecompose.data.CardItem
import com.disheecompose.ui.theme.DisheecomposeTheme

@OptIn(ExperimentalFoundationApi::class)
class PostDetails : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisheecomposeTheme {
                // A surface container using the 'background' color from the theme
                androidx.compose.material3.Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = androidx.compose.material3.MaterialTheme.colorScheme.background
                ) {
                    RecipeScreen()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
fun RecipeScreen() {
    val navController = rememberNavController()

    var isExpanded by remember{ mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            BottomNavigation()
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                RestaurantRating(restaurantName = R.string.app_name)
            }

            item {
                VideoSection(
                    R.drawable.healthy_food,
                    "This hotel is owned by Kaparo. Order healthy food here" +
                            "This hotel is owned by Kaparo. Order healthy food here" +
                            "This hotel is owned by Kaparo. Order healthy food here"
                )
            }

            item {
                Text(text = "Popular Menu", modifier = Modifier.padding(16.dp))
            }

            item {
                CardRow(
                    CardItem(
                        R.drawable.healthy_food,
                        "Special 1",
                        "Description for Special 1"
                    ),
                    CardItem(
                        R.drawable.vegan_resto,
                        "Special 2",
                        "Description for Special 2"
                    )

                )
            }

            item {
                ExpandableCardsRow(
                    cardList = listOf(
                        CardItem(
                            R.drawable.healthy_food,
                            "Special 1",
                            "Description for Special 1"
                        ),
                        CardItem(
                            R.drawable.vegan_resto,
                            "Special 2",
                            "Description for Special 2"
                        ),
                        CardItem(
                            R.drawable.healthy_food,
                            "Special 3",
                            "Description for Special 3"
                        ),
                        CardItem(
                            R.drawable.vegan_resto,
                            "Special 4",
                            "Description for Special 4"
                        )
                    ),
                    isExpanded
                )
            }
        }
    }


}



val items = listOf(
    BottomNavItem("Feed", Icons.Filled.Home),
    BottomNavItem("My Account", Icons.Rounded.Person),
    BottomNavItem("Cart", Icons.Filled.ShoppingCart),
    BottomNavItem("Messages", Icons.Default.Email)
)

@OptIn(ExperimentalFoundationApi::class)
@Preview(showSystemUi = true)
@Composable
fun PostDetailsPreview(){
    RecipeScreen()
}

/*@Preview(showSystemUi = true)
@Composable
fun AppPreview(){
    ExpandableCardsRow(
        cardList = listOf(
            CardItem(
                R.drawable.healthy_food,
                "Special 1",
                "Description for Special 1"
            ),
            CardItem(
                R.drawable.vegan_resto,
                "Special 2",
                "Description for Special 2"
            ),
            CardItem(
                R.drawable.healthy_food,
                "Special 3",
                "Description for Special 3"
            ),
            CardItem(
                R.drawable.vegan_resto,
                "Special 4",
                "Description for Special 4"
            )
        )
    )
}*/



