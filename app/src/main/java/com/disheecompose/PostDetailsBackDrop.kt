package com.disheecompose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.disheecompose.data.CardItem
import com.disheecompose.data.Comment

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PostDetailsBackDrop(){
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
    /*LaunchedEffect(scaffoldState) {
        scaffoldState.conceal()
    }*/

    BackdropScaffold(
        peekHeight = 0.dp,
        headerHeight = 0.dp,
        scaffoldState = scaffoldState,
        appBar = { /*TODO*/ },
        backLayerContent = {
            Box(modifier = Modifier.aspectRatio(16f / 9)){
                // Here you could use a video player library like ExoPlayer to display the video
                // For simplicity, I'll use an Image composable to represent the video thumbnail

                Image(
                    painter = painterResource(id = R.drawable.healthy_food),
                    contentDescription = "Video Thumbnail",
                    modifier = Modifier
                        .fillMaxSize()
                        .heightIn(min = 300.dp),
                    contentScale = ContentScale.Crop
                )
            }
       },
        frontLayerContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 500.dp)
                    .padding(16.dp)
            ) {
                Row {
                    Card {
                        Text(text = "Popular")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(Icons.Default.LocationOn, contentDescription = null)
                    Icon(Icons.Filled.Favorite, contentDescription =null)
                }


                Text(text = stringResource(id = R.string.vegan_resto))
                Text(
                    text = "6 star hotel",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(16.dp)
                        .wrapContentHeight(),
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis
                )


                Row {
                    Text(text = "Popular Menu", modifier = Modifier.padding(16.dp))
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "View All")
                }

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

                Text(text = "Comments")

                CommentColumn(
                    Comment(R.drawable.profilepic, "Njogu", "Whack"),
                    Comment(R.drawable.profilepic, "Karuchiu", "Comida Interesante")
                )
            }

        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PostPreview(){
    PostDetailsBackDrop()
}