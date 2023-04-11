package com.disheecompose.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.disheecompose.R
import com.disheecompose.Utils
import com.disheecompose.models.OrderData
import com.disheecompose.ui.theme.*

/*
* TODO
*  1. Refer to PostDetailScreen to modify MyOrders and OrderCard (vararg)
*  */

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    onPlaceOrderClick: () -> Unit,
    onNavigateBack: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        Spacer(modifier = modifier.height(5.dp))

        MoveBackIcon(onNavigateBack = onNavigateBack)

        MyOrders(orders = Utils.myOrders)

        Spacer(modifier = modifier.weight(1f))

        PaymentDetailsCard(onPlaceOrderClick = onPlaceOrderClick)
    }
}

@Composable
fun MoveBackIcon(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .padding(1.dp)
            .size(40.dp),
        shadowElevation =4.5.dp
    ){
        IconButton(onClick = onNavigateBack) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.back_icon))
        }
    }
}

@Composable
fun MyOrders(
    orders: List<OrderData>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.padding(8.dp)
    ) {
        items(orders) { order ->
            OrderCard(
                orderName = order.orderName,
                image = painterResource(id = order.orderImg),
                restaurant = order.restaurantName,
                price = order.price
            )
        }
    }
}

@Composable
fun PageTitle(
    modifier: Modifier = Modifier
) {
    Text(text = "ORDERS")
}

@Composable
fun OrderCard(
    modifier: Modifier = Modifier,
    orderName: String,
    image: Painter,
    restaurant: String,
    price: Int,
) {

    var quantity: Int = 1
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth()
        ) {
        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = image,
                contentDescription = orderName,
                modifier
                    .size(90.dp)
                    .clip(RectangleShape),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = modifier
                    .defaultMinSize(minWidth = 100.dp, minHeight = 90.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = orderName, style = MaterialTheme.typography.labelLarge)
                Text(text = restaurant, style = MaterialTheme.typography.labelSmall)
                Text(text = "Ksh $price", style = MaterialTheme.typography.labelMedium)
            }

            Row(
                modifier
                    .padding(3.dp)
                    .size(80.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.reduce_icon),
                    contentDescription = "Reduce Icon",
                    modifier
                        .padding(5.dp)
                        .size(20.dp)
                        .clip(RectangleShape)
                )
                Spacer(modifier = modifier.width(3.dp))
                Text(text = "$quantity", modifier.padding(5.dp))
                Spacer(modifier = modifier.width(3.dp))
                Image(
                    painter = painterResource(id = R.drawable.add_icon),
                    contentDescription = "Add Icon",
                    modifier
                        .padding(5.dp)
                        .size(20.dp)
                        .clip(RectangleShape)
                )
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    name = "Order Card Preview",
    showBackground = true
)
@Composable
fun OrderCardPreview() {
    DisheecomposeTheme() {
        OrderCard(
            orderName = "Soup",
            image = painterResource(id = R.drawable.soup),
            restaurant = "Mawimbi", price = 350,
        )
    }
}

@Composable
fun PaymentDetailsCard(
    modifier: Modifier = Modifier,
    onPlaceOrderClick: () -> Unit
){
    Card() {
        Column (
            modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp    )
        ){
            Row() {
                Text(text = "Sub-Total", style = MaterialTheme.typography.labelSmall)
                Spacer(modifier.weight(1f))
                Text(text = "1200", style = MaterialTheme.typography.labelSmall)
            }
            Row() {
                Text(text = "Delivery Charge", style = MaterialTheme.typography.labelSmall)
                Spacer(modifier.weight(1f))
                Text(text = "100", style = MaterialTheme.typography.labelSmall)
            }
            Row() {
                Text(text = "Discount", style = MaterialTheme.typography.labelSmall)
                Spacer(modifier.weight(1f))
                Text(text = "200", style = MaterialTheme.typography.labelSmall)
            }
            Row() {
                Text(text = "Total", style = MaterialTheme.typography.labelMedium)
                Spacer(modifier.weight(1f))
                Text(text = "Kshs 1500", style = MaterialTheme.typography.labelMedium)
            }
            Button(
                onClick = onPlaceOrderClick,
                modifier.fillMaxWidth()
            ) {
                Text(text = "Place My Order", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    "Cart Screen preview",
    showBackground = true
)
@Composable
fun CartScreenPreview() {
    DisheecomposeTheme {
        CartScreen(
            onPlaceOrderClick = {},
            onNavigateBack = {}
        )
    }
}