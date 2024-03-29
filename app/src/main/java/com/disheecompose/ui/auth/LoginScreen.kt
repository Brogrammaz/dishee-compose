package com.disheecompose.ui.auth


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.disheecompose.R
import com.disheecompose.navigation.NavigationDestination
import com.disheecompose.ui.components.OutlinedTextFieldSample
import com.disheecompose.ui.theme.DisheecomposeTheme

object LoginDestination: NavigationDestination {
    override val route = "login"
    override val titleRes: Int = R.string.login
}

@Composable
fun LoginScreen(
    onSignupButtonClicked: () -> Unit,
    onRegisterTextButtonClicked: () -> Unit,
    authViewModel: AuthViewModel = viewModel()
){
    val signInResult by authViewModel.signInResult.collectAsState()
    val focusManager = LocalFocusManager.current

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.welcome_dishee),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 40.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.login_details),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(20.dp)
        )
        Spacer(modifier = Modifier.weight(0.5f))
        OutlinedTextFieldSample(
            value = email,
            onValueChange = {email = it},
            label = R.string.email,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ) ,
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        OutlinedTextFieldSample(
            value = password,
            onValueChange = { password = it},
            label = R.string.pass,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {focusManager.clearFocus()}
            ),
            /*
            TODO, HIDE USER PASSWORD INPUT
            visualTransformation = PasswordVisualTransformation()
            */
        )
        Text(
            text = stringResource(id = R.string.recover_password),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 8.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                authViewModel.signIn(email, password)
                if(signInResult == true){
                    onSignupButtonClicked?.invoke()
                }else{
                   //Show toast
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(id = R.string.sign_in),
                style = MaterialTheme.typography.labelLarge
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.continue_with),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.weight(1f))
        SocialMediaIcons()
        Spacer(modifier = Modifier.weight(1f))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.no_account),
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick =  onRegisterTextButtonClicked) {
                Text(
                    text = stringResource(id = R.string.register),
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    name = "Login Screen Preview",
    showBackground = true,
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES
)

@Composable
fun LoginScreenPreview() {
    DisheecomposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LoginScreen(onSignupButtonClicked = { /*TODO*/ }, onRegisterTextButtonClicked = {})
        }
    }
}


@Composable
fun SocialMediaIcons(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = painterResource(id = R.drawable.gmail),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(50)),
            contentScale = ContentScale.Fit
        )
        Image(
            painter = painterResource(id = R.drawable.twitter),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(50)),
            contentScale = ContentScale.FillBounds
        )
        Image(
            painter = painterResource(id = R.drawable.facebook),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(50)),
            contentScale = ContentScale.Crop
        )
    }
}


