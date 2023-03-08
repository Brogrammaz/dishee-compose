package com.disheecompose

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.disheecompose.data.CardItem

@Composable
fun RestaurantRating(
    @StringRes restaurantName: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier.fillMaxWidth().padding(bottom = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = restaurantName))
        IconButton(onClick = { /* TODO */ }) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Favorite",
                tint = Color.Red
            )
        }
    }
}

@Composable
fun VideoSection(@DrawableRes videoUrl: Int, description: String){
    Column (modifier = Modifier.fillMaxWidth()){
        Box(modifier = Modifier.aspectRatio(16f / 9)){
            // Here you could use a video player library like ExoPlayer to display the video
            // For simplicity, I'll use an Image composable to represent the video thumbnail

            Image(
                painter = painterResource(id = videoUrl),
                contentDescription = "Video Thumbnail",
                modifier = Modifier
                    .fillMaxSize()
                    .heightIn(min = 300.dp),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(16.dp)
                .wrapContentHeight(),
            maxLines = 6,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(text = "Reactions")
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Like Button",
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "25",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_chat_24),
                        contentDescription = "Like Button",
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "8",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            Icon(
                Icons.Filled.Share,
                contentDescription = "Share Button",
                modifier = Modifier.size(16.dp),
            )
        }
    }
}

/*@Composable
fun CardRow(
    cardItem1: CardItem,
    cardItem2: CardItem,
    modifier: Modifier = Modifier
){
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CardsItem(imageResId = cardItem1.imageResId, title = cardItem1.title, description = cardItem1.description)
        CardsItem(imageResId = cardItem2.imageResId, title = cardItem2.title, description = cardItem2.description)
    }
}*/

@Composable
fun CardRow(vararg items: CardItem) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(items.size) { index ->
            Card(
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.size(180.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(items[index].imageResId),
                        contentDescription = null,
                        modifier = Modifier.size(120.dp).padding(top = 8.dp)
                    )
                    Text(
                        text = items[index].title,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = items[index].description,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CardsItem(
    imageResId: Int,
    title: String,
    description: String,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column{
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                modifier = Modifier
                    .height(120.dp)
                    .padding(top = 10.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = description,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Composable
fun ExpandableCardsRow(
    cardList: List<CardItem>,
    expanded: Boolean
){
    Column(modifier = Modifier.fillMaxWidth()) {
        //CardRow(cardList.subList(0, if (expanded) cardList.size else 2))
        if (!expanded) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "View All",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable(onClick = { !expanded })
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                content = {
                          items(cardList){
                              CardsItem(
                                  imageResId = it.imageResId,
                                  title = it.title,
                                  description = it.description
                              )
                          }
                },
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )
        }
    }
}


