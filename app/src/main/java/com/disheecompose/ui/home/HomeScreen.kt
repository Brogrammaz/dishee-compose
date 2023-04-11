package com.disheecompose.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.disheecompose.ui.components.BottomNavigation
import com.disheecompose.R
import com.disheecompose.ui.theme.DisheecomposeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onRestaurantOnClick: () -> Unit = {},
    onSpecialDealOnClick: () -> Unit,
    onCartScreenNavigation: () -> Unit
){

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
                onRestaurantOnClick = onRestaurantOnClick
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
    modifier: Modifier = Modifier,
    onRestaurantOnClick: () -> Unit = {}
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
            NearRestaurantCard(
                imageRes = R.drawable.healthy_food,
                restaurantName = R.string.healthy_food,
                distance = R.string.min_12,
                onRestaurantOnClick = onRestaurantOnClick
            )
            Spacer(modifier = modifier.weight(1f))
            NearRestaurantCard(
                imageRes = R.drawable.vegan_resto,
                restaurantName = R.string.vegan_resto,
                distance = R.string.min_8,
                onRestaurantOnClick = onRestaurantOnClick
            )
        }
    }
}

@Composable
fun NearRestaurantCard(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int,
    @StringRes restaurantName: Int,
    @StringRes distance: Int,
    onRestaurantOnClick: () -> Unit = {}
){
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .size(180.dp)
            .clickable(onClick = onRestaurantOnClick)
    ) {
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                modifier = modifier.size(80.dp),
                painter = painterResource(id = imageRes),
                contentDescription = null
            )
            Text(
                text = stringResource(id = restaurantName),
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = stringResource(id = distance),
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