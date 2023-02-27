package com.disheecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.disheecompose.ui.theme.DisheecomposeTheme

class UploadPicture : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisheecomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UploadPictureScreen()
                }
            }
        }
    }
}

@Composable
fun UploadPictureScreen(){
   /* var profilePicture by remember{
        mutableStateOf(R.drawable.profilepic)
    }*/
    Column (
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.weight(1f))
        UserUpload(
            //profilePic = profilePicture
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun UserUpload(
    modifier: Modifier = Modifier,
    //profilePic: Int
){
    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.upload_picture),
            modifier = modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 50.sp
        )
        Image(
            painter = painterResource(id = R.drawable.profilepic),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
        )
        Text(
            text = stringResource(id = R.string.upload_data),
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}