package com.disheecompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrderDetailBackDrop(){
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)

    BackdropScaffold(
        peekHeight = 0.dp,
        headerHeight = 0.dp,
        scaffoldState = scaffoldState,
        appBar = { },
        backLayerContent = {
            Box(modifier = Modifier.aspectRatio(16f / 9)){
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
            Column {
                Row {
                    Card {
                        Text(text = "Popular")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(Icons.Default.LocationOn, contentDescription = null)
                    Icon(Icons.Filled.Favorite, contentDescription =null)
                }

                Text(text = stringResource(id = R.string.vegan_resto), maxLines = 2)
                Text(text = "Rating implementation")

                OrderDescription(
                    description = stringResource(id = R.string.demo_order_description),

                )

                ExtendedFloatingActionButton(
                    text = { Text("FLUID FAB") },
                    onClick = { /*do something*/ },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
    )
}

@Composable
fun OrderDescription(description: String){
    Text(text = description, softWrap = true)
}

@Preview
@Composable
fun OrderPreview(){
    OrderDetailBackDrop()
}