package com.disheecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.disheecompose.models.FeedData
import com.disheecompose.ui.theme.DisheecomposeTheme
import java.net.URL

class Feed : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisheecomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting2("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    DisheecomposeTheme {
        Greeting2("Android")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(modifier: Modifier = Modifier) {

//    Scaffold(
//        bottomBar = {
//            BottomAppBar() {
//
//            }
//        }
//    ) {
//        it -> LazyColumn(){
//
//    }
//
//    }


}

@Composable
fun FeedPage(modifier: Modifier, posts: List<FeedData>) {

    LazyColumn(modifier = modifier.padding(4.dp)) {
        items(posts) { post ->
            Post(companyName = "", caption = "", feedImg = painterResource(id = R.drawable.tabata))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Post(
    modifier: Modifier = Modifier,
    companyName: String,
    companyLogo: Painter = painterResource(id = R.drawable.tabata),
    caption: String,
    feedImg: Painter
) {

    Card(onClick = { /*TODO*/ }) {
        Column(modifier) {
            LogoTitle(companyName = companyName, companyLogo = companyLogo)
            Spacer(modifier.height(10.dp))
            FeedCard(caption = caption, feedImg = feedImg)
        }
    }
}

@Composable
fun LogoTitle(
    modifier: Modifier = Modifier,
    companyName: String,
    companyLogo: Painter
) {

    Row(modifier) {
        Image(
            painter = companyLogo,
            contentDescription = "Company Logo",
            modifier
                .size(20.dp)
                .clip(RectangleShape),

            )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = companyName, style = MaterialTheme.typography.bodyMedium)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedCard(
    modifier: Modifier = Modifier,
    caption: String,
    feedImg: Painter
) {
    Box(modifier = Modifier.height(40.dp)) {
        Card(onClick = { /*TODO navigate*/ }) {
            Image(painter = feedImg, contentDescription = "Post image")

        }
        Box(modifier.align(Alignment.Center)) {
            Text(text = stringResource(id = R.string.view_post))
        }
    }
}

@Composable
fun FeedBottomNavigation(
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier.background(MaterialTheme.colorScheme.background)
            .padding(4.dp)
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = "User Icon")
            },
            modifier = modifier.padding(horizontal = 2.dp)

        )

        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "User Icon")
            },
            modifier = modifier.padding(horizontal = 2.dp)
        )


        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "User Icon")
            },
            modifier = modifier.padding(horizontal = 2.dp)
        )

        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(imageVector = Icons.Default.Notifications, contentDescription = "User Icon")
            },
            modifier = modifier.padding(horizontal = 2.dp)
        )

    }
}

@Preview(
    name = "Bottom Nav Preview",
    showBackground = true
)
@Composable
fun BottomNavPreview() {
    DisheecomposeTheme() {
        Surface {
            FeedBottomNavigation()
        }
    }
}