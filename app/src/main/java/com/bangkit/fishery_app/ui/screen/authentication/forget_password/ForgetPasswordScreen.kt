package com.bangkit.fishery_app.ui.screen.authentication.forget_password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.ui.components.LoadingDialog
import com.bangkit.fishery_app.util.emailValidation

@Composable
fun ForgetPasswordScreen(
    modifier: Modifier = Modifier,
    moveToLogin: () -> Unit,
    viewModel: ForgetPasswordViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ForgetPasswordContent(
        email = state.email,
        onEmailChanged = { email ->
            viewModel.onEvent(ForgetPasswordEvent.OnEmailChanged(email))
        },
        onResetPassword = {
            viewModel.onEvent(ForgetPasswordEvent.ForgetPasswordWithEmail(state.email))
        },
        moveToLogin = moveToLogin,
        modifier = modifier,
    )
    if (state.isLoading) {
        LoadingDialog()
    }
}

@Composable
fun ForgetPasswordContent(
    modifier: Modifier = Modifier,
    email: String,
    onEmailChanged: (email: String) -> Unit,
    onResetPassword: () -> Unit,
    moveToLogin: () -> Unit,
) {

    var isEmailValid by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 104.dp, start = 24.dp, end = 24.dp),
    ) {

        Text(
            text = stringResource(id = R.string.forgot_password_title),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = stringResource(id = R.string.forgot_password_desc),
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.padding(top = 16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                onEmailChanged(it)
                isEmailValid = emailValidation(it)
            },
            modifier = modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            label = {
                Text(text = stringResource(id = R.string.email))
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            ),
            shape = RoundedCornerShape(24.dp),
            isError = !isEmailValid,
        )

        if (!isEmailValid) {
            Text(
                text = stringResource(id = R.string.invalid_email),
                color = MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = onResetPassword,
            modifier = modifier
                .padding(top = 32.dp)
                .fillMaxWidth(),
        ) {
            Text(text = stringResource(id = R.string.send_email))
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 200.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.remember_password),
                style = MaterialTheme.typography.bodyMedium
            )
            TextButton(onClick = moveToLogin) {
                Text(
                    text = stringResource(id = R.string.login),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }


}