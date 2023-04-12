package com.disheecompose.ui.home

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.disheecompose.R
import com.disheecompose.models.Restaurant
import com.disheecompose.navigation.NavigationDestination
import com.disheecompose.ui.components.BottomNavigation

object HomeDestination: NavigationDestination{
    override val route = "home"
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onRestaurantOnClick: (Int) -> Unit ,
    onSpecialDealOnClick: () -> Unit,
    onCartScreenNavigation: () -> Unit,
    viewModel: HomeViewModel = viewModel()
){
    val homeUiState by viewModel.uiState.collectAsState()

    val focusManager = LocalFocusManager.current

    var orderWant by remember{
        mutableStateOf("")
    }

    Scaffold(
        bottomBar = {
            BottomNavigation(
                onCartScreenNavigation = onCartScreenNavigation
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Row(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.find_fav_food),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    Icons.Outlined.Notifications,
                    contentDescription = null,
                    modifier = modifier
                        .size(40.dp)
                        .align(Alignment.CenterVertically)
                )
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
            ){
                OutlinedTextField(
                    value = orderWant,
                    onValueChange = {orderWant = it},
                    label = {
                        Text(
                            text = stringResource(id = R.string.what_order),
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {focusManager.clearFocus()}
                    ),
                    leadingIcon = {
                        Icon(Icons.Outlined.Search, contentDescription = null)
                    }
                )
                Spacer(modifier = modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.filter_icon),
                    contentDescription = null,
                    modifier = modifier
                        .size(40.dp)
                        .align(Alignment.CenterVertically)
                )
            }

            SpecialDealCard(
                imageRes = R.drawable.profilepic,
                onSpecialDealOnClick = onSpecialDealOnClick
            )

            NearestRestaurantArea(
                restaurantList = homeUiState.restaurantList,
                onRestaurantOnClick = { onRestaurantOnClick(it.id) }
            )
        }
    }


}

@Composable
fun SpecialDealCard(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int,
    onSpecialDealOnClick: () -> Unit = {}
){
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {
        Row(
            modifier.padding(10.dp)
        ) {
            Image(painter = painterResource(id = imageRes), contentDescription = null)
            Spacer(modifier = modifier.weight(1f))
            Column(
                modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    text = stringResource(id = R.string.deal_month),
                    style = MaterialTheme.typography.labelMedium
                )
                Button(onClick = onSpecialDealOnClick) {
                    Text(
                        text = stringResource(id = R.string.buy_now),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Composable
fun NearestRestaurantArea(
    restaurantList: List<Restaurant>,
    modifier: Modifier = Modifier,
    onRestaurantOnClick: (Restaurant) -> Unit = {}
){
    Column {
        Row(
            modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.near_restaurant),
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = modifier.weight(1f))
        }
        Row(
            modifier.fillMaxWidth()
        ) {
            NearRestaurantCard(restaurant = restaurantList[0], onRestaurantOnClick = onRestaurantOnClick)
            Spacer(modifier = modifier.weight(1f))
            NearRestaurantCard(restaurant = restaurantList[1], onRestaurantOnClick = onRestaurantOnClick)
        }
    }
}

@Composable
fun NearRestaurantCard(
    restaurant: Restaurant,
    modifier: Modifier = Modifier,
    onRestaurantOnClick: (Restaurant) -> Unit
){
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .size(180.dp)
            .clickable{ onRestaurantOnClick(restaurant) }
    ) {
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                modifier = modifier.size(80.dp),
                painter = painterResource(id = restaurant.imageResId),
                contentDescription = null
            )
            Text(
                text = stringResource(id = restaurant.title),
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = stringResource(id = restaurant.distance),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

/*
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun Appreview(){
    DisheecomposeTheme {
        HomeScreen()
    }
}*/
