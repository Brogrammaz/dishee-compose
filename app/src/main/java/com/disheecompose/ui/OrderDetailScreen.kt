package com.disheecompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.disheecompose.R
import com.disheecompose.ui.theme.DisheeComposeTheme2

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrderDetailScreen(
    onAddToCartClick: () -> Unit
){
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val layoutDirection = LocalLayoutDirection.current

    DisheeComposeTheme2 {
        Box(modifier = Modifier.fillMaxSize()) {
            BackdropScaffold(
                peekHeight = 0.dp,
                headerHeight = 0.dp,
                scaffoldState = scaffoldState,
                appBar = { },
                backLayerContent = {
                    BoxWithConstraints(
                        modifier = Modifier
                            .fillMaxHeight(0.35f)
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.medium)
                    ) {
                        val height = if (maxHeight > screenHeight / 2) screenHeight / 2 else maxHeight
                        Image(
                            painter = painterResource(id = R.drawable.healthy_food),
                            contentDescription = "Video Thumbnail",
                            modifier = Modifier
                                .height(height)
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    }
                },
                frontLayerContent = {
                    BoxWithConstraints(
                        modifier = Modifier
                            .fillMaxHeight(0.65f)
                            .fillMaxWidth()
                    ){
                        val constraints = maxHeight / 2
                        val height = if (constraints > screenHeight / 2) screenHeight / 2 else constraints
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .height(height),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Row {
                                Card {
                                    Text(
                                        text = "Popular",
                                        style = androidx.compose.material3.MaterialTheme.typography.labelSmall
                                    )
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                Icon(Icons.Default.LocationOn, contentDescription = null)
                                Icon(Icons.Filled.Favorite, contentDescription =null)
                            }

                            Text(
                                text = stringResource(id = R.string.vegan_resto),
                                style = androidx.compose.material3.MaterialTheme.typography.titleSmall,
                                maxLines = 2
                            )
                            Text(
                                text = "Rating implementation",
                                style = androidx.compose.material3.MaterialTheme.typography.labelSmall
                            )

                            OrderDescription(
                                description = stringResource(id = R.string.demo_order_description),
                            )
                        }
                    }
                },
            )

            ExtendedFloatingActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    .padding(start = 16.dp, end = 16.dp, bottom = 100.dp),
                text = {
                    Text(
                        "Add To Cart",
                        style = androidx.compose.material3.MaterialTheme.typography.labelLarge
                    )
               },
                onClick = onAddToCartClick,
            )
        }
    }
}

@Composable
fun OrderDescription(description: String){
    Text(
        text = description,
        style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
        softWrap = true
    )
}

@Preview
@Composable
fun OrderPreview(){
    OrderDetailScreen(
        onAddToCartClick = {}
    )
}