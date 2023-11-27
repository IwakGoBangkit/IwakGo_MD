package com.bangkit.fishery.ui.screen.authentication.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.bangkit.fishery.R
import com.bangkit.fishery.util.emailValidation

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    moveToRegister: () -> Unit,
    moveToHome: () -> Unit
) {
    LoginContent(
        moveToRegister = moveToRegister,
        moveToHome = moveToHome
    )
}

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    moveToRegister: () -> Unit,
    moveToHome: () -> Unit
) {
    var email by remember {
        mutableStateOf("")
    }

    var isEmailValid by remember {
        mutableStateOf(true)
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 104.dp, start = 24.dp, end = 24.dp),
    ) {

        Text(
            stringResource(R.string.login),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold
        )

        OutlinedTextField(
            value = email,
            onValueChange = { newEmail ->
                email = newEmail
                isEmailValid = emailValidation(newEmail)
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            label = { Text(stringResource(R.string.email)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            ),
            shape = RoundedCornerShape(24.dp),
            isError = !isEmailValid,
        )

        if (!isEmailValid) {
            Text(stringResource(R.string.invalid_email), color = Color.Red)
        }

        OutlinedTextField(
            value = password,
            onValueChange = { newPassword -> password = newPassword },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            label = { Text(stringResource(R.string.password)) },
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        painter = if (passwordVisibility) painterResource(R.drawable.ic_hide_password) else painterResource(
                            R.drawable.ic_show_password
                        ),
                        contentDescription = null
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default,
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            shape = RoundedCornerShape(24.dp)
        )

        Button(
            onClick = moveToHome,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            Text(stringResource(R.string.login))
        }

        Text(
            stringResource(R.string.or),
            modifier = modifier
                .align(CenterHorizontally)
                .padding(top = 32.dp)
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_google),
                contentDescription = null,
                modifier = modifier.padding(end = 8.dp)
            )
            Text(stringResource(R.string.login_with_google))
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 144.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(R.string.have_not_account),
            )
            TextButton(
                onClick = moveToRegister,
            ) {
                Text(stringResource(R.string.register))
            }
        }
    }
}

