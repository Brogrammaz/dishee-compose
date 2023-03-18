package com.disheecompose.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.disheecompose.R
import com.disheecompose.ui.theme.DisheecomposeTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Confirm Order")},
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(
            modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            //Text(text = "Confirm Order")
            Spacer(modifier = modifier.padding(30.dp))
            DeliverToCard()
            Spacer(modifier = modifier.padding(20.dp))
            PaymentMethodCard(accountNumber = "1234567890")
            Spacer(modifier = modifier.weight(1f))
            PaymentDetailsCard()
        }

    }

}

@Composable
fun DeliverToCard(
    modifier: Modifier = Modifier
){
    Surface(
        shadowElevation = 10.dp
    ){
        Column(
            modifier.padding(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Deliver To")
                Spacer(modifier.weight(1f))
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Edit")
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.LocationOn, contentDescription = null)
                Spacer(modifier.padding(12.dp))
                Text(
                    text = "Dennis Prit Garden Apaartment 205\n" +
                        "Call on arrival",
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
fun PaymentMethodCard(
    modifier: Modifier = Modifier,
    accountNumber: String
){
    Surface(
        shadowElevation = 10.dp
    ) {
        Column (
            modifier.padding(10.dp),

        ){
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Payment Method")
                Spacer(modifier.weight(1f))
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Edit")
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(id = R.drawable.profilepic), contentDescription = null)
                Spacer(modifier.weight(1f))
                Text(text = accountNumber )
            }
        }
    }
}

@Composable
fun PaymentDetailsCard(
    modifier: Modifier = Modifier
){
    Card() {
        Column (
            modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp    )
        ){
            Row() {
                Text(text = "Sub-Total")
                Spacer(modifier.weight(1f))
                Text(text = "1200")
            }
            Row() {
                Text(text = "Delivery Charge")
                Spacer(modifier.weight(1f))
                Text(text = "100")
            }
            Row() {
                Text(text = "Discount")
                Spacer(modifier.weight(1f))
                Text(text = "200")
            }
            Row() {
                Text(text = "Total")
                Spacer(modifier.weight(1f))
                Text(text = "Kshs 1500")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier.fillMaxWidth()
            ) {
                Text(text = "Place My Order")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PaymentPreview(){
    DisheecomposeTheme() {
        PaymentScreen()
    }
}