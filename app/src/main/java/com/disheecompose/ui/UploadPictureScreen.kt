package com.disheecompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.disheecompose.R
import com.disheecompose.ui.theme.DisheecomposeTheme

@Composable
fun UploadPictureScreen(
    onNextButtonClicked: () -> Unit = {}
){
   /* var profilePicture by remember{
        mutableStateOf(R.drawable.profilepic)
    }*/
    Column (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.weight(1f))
        UserUpload(
            onNextButtonClicked = onNextButtonClicked
            //profilePic = profilePicture
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(
    name = "Upload Picture Preview",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun UploadPicturePreview() {
    DisheecomposeTheme {
        UploadPictureScreen()
    }
}

@Composable
fun UserUpload(
    modifier: Modifier = Modifier,
    onNextButtonClicked: () -> Unit
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
            onClick = onNextButtonClicked,
            modifier = modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}