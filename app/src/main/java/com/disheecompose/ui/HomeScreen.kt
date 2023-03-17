package com.disheecompose.ui

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
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
import com.disheecompose.BottomNavigation
import com.disheecompose.R
import com.disheecompose.ui.theme.DisheecomposeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier){

    val focusManager = LocalFocusManager.current

    var orderWant by remember{
        mutableStateOf("")
    }

    Scaffold(
        bottomBar = {
            BottomNavigation()
        }
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Row(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.find_fav_food),
                    fontSize = 40.sp
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
                        Text(text = stringResource(id = R.string.what_order))
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

            SpecialDealCard(imageRes = R.drawable.profilepic)

            NearestRestaurantArea()
        }
    }


}

@Composable
fun SpecialDealCard(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int,
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
                Text(text = stringResource(id = R.string.deal_month))
                Button(onClick = { /*ToDo*/ }) {
                    Text(text = stringResource(id = R.string.buy_now))
                }
            }
        }
    }
}

@Composable
fun NearestRestaurantArea(
    modifier: Modifier = Modifier
){
    Column {
        Row(
            modifier.fillMaxWidth().padding(bottom = 10.dp)
        ) {
            Text(text = stringResource(id = R.string.near_restaurant))
            Spacer(modifier = modifier.weight(1f))
            Text(text = stringResource(id = R.string.view_more))
        }
        Row(
            modifier.fillMaxWidth()
        ) {
            NearRestaurantCard(
                imageRes = R.drawable.healthy_food,
                restaurantName = R.string.healthy_food,
                distance = R.string.min_12
            )
            Spacer(modifier = modifier.weight(1f))
            NearRestaurantCard(
                imageRes = R.drawable.vegan_resto,
                restaurantName = R.string.vegan_resto,
                distance = R.string.min_8
            )
        }
    }
}

@Composable
fun NearRestaurantCard(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int,
    @StringRes restaurantName: Int,
    @StringRes distance: Int
){
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .size(180.dp)
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
            Text(text = stringResource(id = restaurantName))
            Text(text = stringResource(id = distance))
        }
    }
}

@Preview
@Composable
fun Appreview(){
    DisheecomposeTheme {
        HomeScreen()
    }
}