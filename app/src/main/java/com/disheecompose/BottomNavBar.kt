package com.disheecompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier
){
    BottomAppBar(
        modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(4.dp)
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = "User Icon")
            },
            modifier.padding(horizontal = 2.dp)
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

