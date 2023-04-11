package com.disheecompose.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.disheecompose.R
import com.disheecompose.models.CardItem
import com.disheecompose.models.Comment
import com.disheecompose.ui.components.CardRow
import com.disheecompose.ui.components.CommentColumn
import com.disheecompose.ui.components.RestaurantDetails
import com.disheecompose.ui.theme.DisheeComposeTheme2
import com.disheecompose.ui.theme.DisheecomposeTheme

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PostDetailScreen(
    onOrderClick: () -> Unit = {}
){


    val scaffoldState = rememberBackdropScaffoldState(
        BackdropValue.Revealed)
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    /*LaunchedEffect(scaffoldState) {
        scaffoldState.conceal()
    }*/

    DisheeComposeTheme2(){

        BackdropScaffold(
            peekHeight = 0.dp,
            headerHeight = 0.dp,
            //frontLayerBackgroundColor = MaterialTheme.colors.surface,
            scaffoldState = scaffoldState,
            appBar = { /*TODO*/ },
            backLayerContent = {
                Box(modifier = Modifier
                    .aspectRatio(16f / 9)
                    .fillMaxSize()
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                ){
                    // Here you could use a video player library like ExoPlayer to display the video
                    // For simplicity, I'll use an Image composable to represent the video thumbnail

                    Image(
                        painter = painterResource(id = R.drawable.healthy_food),
                        contentDescription = "Video Thumbnail",
                        modifier = Modifier
                            .fillMaxSize(),
                        //.height(height),
                        contentScale = ContentScale.Crop
                    )
                }
            },
            frontLayerContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    RestaurantDetails(
                        restaurantName = stringResource(id = R.string.vegan_resto),
                        description = "This hotel is owned by Kaparo. Order healthy food here" +
                                "This hotel is owned by Kaparo. Order healthy food here" +
                                "This hotel is owned by Kaparo. Order healthy food here")

                    Row {
                        Text(text = "Popular Menu", style = androidx.compose.material3.MaterialTheme.typography.labelMedium)
                        Spacer(modifier = Modifier.weight(1f))
                    }

                    CardRow(
                        CardItem(
                            R.drawable.healthy_food,
                            "Special 1",
                            "Ksh 750"
                        ),
                        CardItem(
                            R.drawable.vegan_resto,
                            "Special 2",
                            "Ksh 370"
                        ),
                        onOrderClick = onOrderClick
                    )

                    Text(
                        text = "Comments",
                        style = androidx.compose.material3.MaterialTheme.typography.labelMedium
                    )

                    CommentColumn(
                        Comment(R.drawable.profilepic, "Njogu", "Whack"),
                        Comment(R.drawable.profilepic, "Karuchiu", "Comida Interesante"),
                        Comment(R.drawable.profilepic, "Njogu", "Whack"),
                        Comment(R.drawable.profilepic, "Karuchiu", "Comida Interesante"),
                        Comment(R.drawable.profilepic, "Njogu", "Whack"),
                        Comment(R.drawable.profilepic, "Karuchiu", "Comida Interesante"),
                        Comment(R.drawable.profilepic, "Njogu", "Whack"),
                        Comment(R.drawable.profilepic, "Karuchiu", "Comida Interesante")
                    )
                }

            }
        )
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PostPreview(){
    DisheecomposeTheme() {
        PostDetailScreen()
    }
}