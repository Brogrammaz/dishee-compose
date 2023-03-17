package com.disheecompose.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.disheecompose.R
import com.disheecompose.data.CardItem
import com.disheecompose.data.Comment
import java.time.Instant
import java.util.*

@Composable
fun RestaurantRating(
    @StringRes restaurantName: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp),
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
fun RestaurantDetails(restaurantName: String, description: String){
    Column (modifier = Modifier.fillMaxWidth()){
        Row {
            androidx.compose.material.Card {
                androidx.compose.material.Text(text = "Popular")
            }
            Spacer(modifier = Modifier.weight(1f))
            androidx.compose.material.Icon(Icons.Default.LocationOn, contentDescription = null)
            androidx.compose.material.Icon(Icons.Filled.Favorite, contentDescription = null)
        }

        androidx.compose.material.Text(text = restaurantName)
        androidx.compose.material.Text(
            text = "6 star hotel",
            style = androidx.compose.material.MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(16.dp)
                .wrapContentHeight(),
            maxLines = 6,
            overflow = TextOverflow.Ellipsis
        )

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
fun CardRow(
    vararg items: CardItem,
    onOrderClick: () -> Unit = {}
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(items.size) { index ->
            CardsItem(
                cardItem = CardItem(items[index].imageResId, items[index].title, items[index].description),
                onOrderClick = onOrderClick
            )
        }
    }
}

@Composable
fun CardsItem(
    cardItem: CardItem,
    modifier: Modifier = Modifier,
    onOrderClick: () -> Unit = {}
){
    Card(
        modifier = modifier
            .size(180.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onOrderClick),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(4.dp).align(Alignment.CenterHorizontally)
        ){
            Image(
                painter = painterResource(id = cardItem.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = cardItem.title,
                modifier = Modifier.padding(top = 4.dp),
                style = MaterialTheme.typography.headlineMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = cardItem.description,
                modifier = Modifier.padding(top = 4.dp),
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
            //What if TextButton.
            //Look on Woof
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
                        .clickable(onClick = { expanded })
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                content = {
                          items(cardList){
                              CardsItem(
                                  cardItem = it
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CommentCard(
    comment: Comment,
    modifier: Modifier = Modifier
){
    Card() {
        Row() {
            Icon(painter = painterResource(id = comment.userImage), contentDescription = null)
            Column(
                modifier.padding(8.dp)
            ) {
                Text(text = comment.userName)
                Text(text = Date.from(Instant.now()).toString())

                Text(
                    text = comment.comment,
                    maxLines = 4
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CommentColumn(vararg comments: Comment){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        items(comments.size){ index ->
            CommentCard(
                comment = Comment(
                    comments[index].userImage,
                    comments[index].userName,
                    comments[index].comment
                )
            )
        }
    }
}
