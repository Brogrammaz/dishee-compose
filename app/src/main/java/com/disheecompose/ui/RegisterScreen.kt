package com.disheecompose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.unit.sp
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
            fontSize = 32.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 40.dp)
        )
        Text(
            text = stringResource(id = R.string.register_details),
            fontSize = 24.sp,
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
            Text(text = stringResource(id = R.string.have_account))
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick =  onLoginTextButtonClicked) {
                Text(text = stringResource(id = R.string.login))
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
    Row() {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(text = stringResource(id = R.string.terms_conditions))
    }
}

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