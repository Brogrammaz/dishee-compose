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
    onPlaceOrderClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        Spacer(modifier = modifier.height(5.dp))

        MoveBackIcon()

        MyOrders(orders = Utils.myOrders)

        Spacer(modifier = modifier.weight(1f))

        PlaceOrderCard(onPlaceOrderClick = onPlaceOrderClick)
    }
}

@Composable
fun MoveBackIcon(
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .padding(1.dp)
            .size(40.dp),
        shadowElevation =4.5.dp
    ){
        IconButton(onClick = {

        }) {
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
                Text(text = orderName, fontSize = 15.sp)
                Text(text = restaurant, fontSize = 10.sp)
                Text(text = "Ksh $price", fontSize = 20.sp)
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
fun PlaceOrderCard(
    modifier: Modifier = Modifier,
    subTotal: Int = 0,
    deliveryFee: Int = 0,
    discount: Int = 0,
    totals: Int = 0,
    onPlaceOrderClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.padding(5.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp), Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.sub_total),
                    fontSize = 15.sp,
                    modifier = modifier.padding(top = 10.dp)
                )
                Text(
                    text = "$subTotal",
                    modifier = modifier.padding(top = 10.dp)
                )
            }

            Spacer(modifier = modifier.height(5.dp))

            Row(
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp), Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.delivery_charge),
                    fontSize = 15.sp
                )
                Text(text = "$deliveryFee")
            }

            Spacer(modifier = modifier.height(5.dp))

            Row(
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp), Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.discount),
                    fontSize = 15.sp,
                )
                Text(text = "$discount")
            }

            Spacer(modifier = modifier.height(5.dp))

            Row(
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp), Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.total),
                    fontSize = 15.sp,
                )
                Text(text = "Ksh $totals")
            }

            Spacer(modifier = modifier.height(10.dp))

            Button(
                onClick =  onPlaceOrderClick,
                modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                shape = MaterialTheme.shapes.medium,
            ) {
                Text(text = stringResource(id = R.string.place_order))
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
            onPlaceOrderClick = {}
        )
    }
}