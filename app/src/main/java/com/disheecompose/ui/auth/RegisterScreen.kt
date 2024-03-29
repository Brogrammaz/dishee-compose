package com.disheecompose.ui.auth

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.disheecompose.R
import com.disheecompose.ui.components.OutlinedTextFieldSample
import com.disheecompose.ui.theme.DisheecomposeTheme

@Composable
fun RegisterScreen(
    onRegisterButtonClicked: () -> Unit,
    onLoginTextButtonClicked: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    var firstName by remember {
        mutableStateOf("")
    }

    var lastName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    var checked by remember {
        mutableStateOf(false)
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround
    ){
        Text(
            text = stringResource(id = R.string.create_account),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 40.dp)
        )
        Text(
            text = stringResource(id = R.string.register_details),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .align(Alignment.Start)
        )
        OutlinedTextFieldSample(
            value = firstName,
            onValueChange = {firstName = it},
            label = R.string.first_name,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ) ,
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            )
        )
        OutlinedTextFieldSample(
            value = lastName,
            onValueChange = {lastName = it},
            label = R.string.last_name,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ) ,
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            )
        )
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
        OutlinedTextFieldSample(
            value = password,
            onValueChange = { password = it},
            label = R.string.pass,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onDone = {focusManager.clearFocus()}
            )
        )
        OutlinedTextFieldSample(
            value = confirmPassword,
            onValueChange = { confirmPassword = it},
            label = R.string.confirm_pass,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {focusManager.clearFocus()}
            )
        )
        TermsAndConditions(checked = checked, onCheckedChange = {checked = it})
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.have_account),
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick =  onLoginTextButtonClicked) {
                Text(
                    text = stringResource(id = R.string.login),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
        Button(
            onClick = onRegisterButtonClicked,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 30.dp)
        ) {
            Text(text = stringResource(id = R.string.register))
        }
    }
}

@Composable
fun TermsAndConditions(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
){
    Row {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(
            text = stringResource(id = R.string.terms_conditions),
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun RegisterScreenPreview(){
    DisheecomposeTheme {
        RegisterScreen(
            onRegisterButtonClicked = { /*TODO*/ },
            onLoginTextButtonClicked = {}
        )
    }
}