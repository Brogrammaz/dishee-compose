package com.disheecompose

import android.content.res.Resources.Theme
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.disheecompose.models.OrderData
import com.disheecompose.ui.theme.*

class Cart : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisheecomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting3("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    DisheecomposeTheme {
        Greeting3("Android")
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
            .fillMaxWidth(),

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
                Text(text = restaurant, fontSize = 10.sp, color = FaintText)
                Text(text = "Ksh $price", fontSize = 20.sp, color = Green8B)
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
fun MoveBackIcon(
    modifier: Modifier = Modifier
) {
   Surface(
       shape = MaterialTheme.shapes.medium,
       modifier = modifier
           .padding(1.dp)
           .size(40.dp),
       shadowElevation =4.5.dp,
       color = FaintPink
   ){
       IconButton(onClick = {

       }) {
           Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.back_icon))
       }
   } 
}

@Preview(
    "Move Back Icon Preview",
    showBackground = true
)
@Composable
fun MoveBackIconPreview() {
    DisheecomposeTheme {
        MoveBackIcon()
    }
}

@Composable
fun PlaceOrderCard(
    modifier: Modifier = Modifier,
    subTotal: Int = 0,
    deliveryFee: Int = 0,
    discount: Int = 0,
    totals: Int = 0
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.padding(5.dp)
    ) {
        Column(
            modifier
                .background(Green8B),
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
                    color = ThemeWhite,
                    modifier = modifier.padding(top = 10.dp)
                )
                Text(
                    text = "$subTotal",
                    color = ThemeWhite,
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
                    fontSize = 15.sp,
                    color = ThemeWhite
                )
                Text(text = "$deliveryFee", color = ThemeWhite)
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
                    color = ThemeWhite
                )
                Text(text = "$discount", color = ThemeWhite)
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
                    color = ThemeWhite
                )
                Text(text = "Ksh $totals", color = ThemeWhite)
            }

            Spacer(modifier = modifier.height(10.dp))

            Button(
                onClick = { },
                modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(White)
            ) {
                Text(text = stringResource(id = R.string.place_order), color = Green8B)
            }
        }
    }

}

@Preview(
    name = "Place Order Card Preview",
)
@Composable
fun PlaceOrderPreview() {

    DisheecomposeTheme {
        PlaceOrderCard(subTotal = 1200, deliveryFee = 100, discount = 200, totals = 1500)
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
fun CartScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        Spacer(modifier = modifier.height(5.dp))

        MoveBackIcon()

        MyOrders(orders = Utils.myOrders)

        PlaceOrderCard()
    }
}

@Preview(
    "Cart Screen preview",
    showBackground = true
)
@Composable
fun CartScreenPreview() {
    DisheecomposeTheme {
        CartScreen()
    }
}