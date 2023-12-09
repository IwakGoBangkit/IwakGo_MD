package com.bangkit.fishery_app.ui.screen.authentication.login

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.ui.components.LoadingDialog
import com.bangkit.fishery_app.ui.screen.authentication.common.loginIntentSender
import com.bangkit.fishery_app.util.emailValidation
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    moveToRegister: () -> Unit,
    moveToHome: () -> Unit,
    moveToForgetPass: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            scope.launch {
                viewModel.onEvent(LoginEvent.LoginWithGoogle(result.data ?: return@launch))
            }
        }
    }

    LoginContent(
        email = state.email,
        password = state.password,
        onEmailChanged = { email ->
            viewModel.onEvent(LoginEvent.OnEmailChanged(email))
        },
        onPasswordChanged = { password ->
            viewModel.onEvent(LoginEvent.OnPasswordChanged(password))
        },
        onLoginWithGoogle = {
            viewModel.onEvent(LoginEvent.SetLoadingState(true))
            scope.launch {
                val loginIntentSender = loginIntentSender(context)
                viewModel.onEvent(LoginEvent.SetLoadingState(false))
                launcher.launch(
                    IntentSenderRequest.Builder(
                        loginIntentSender ?: return@launch
                    ).build()
                )
            }
        },
        loggedIn = {
            viewModel.onEvent(LoginEvent.LoginWithEmailPassword(state.email, state.password))
        },
        moveToRegister = moveToRegister,
        moveToForgetPass = moveToForgetPass,
        modifier = modifier
    )

    LaunchedEffect(state.loginSuccessful) {
        if (state.loginSuccessful) {
            moveToHome()
            viewModel.onEvent(LoginEvent.ResetState)
        }
    }

    if (state.isLoading) {
        LoadingDialog()
    }
}

@Composable
fun LoginContent(
    email: String,
    password: String,
    onEmailChanged: (email: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onLoginWithGoogle: () -> Unit,
    moveToRegister: () -> Unit,
    loggedIn: () -> Unit,
    moveToForgetPass: () -> Unit,
    modifier: Modifier = Modifier
) {

    var isEmailValid by remember {
        mutableStateOf(true)
    }

    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier.fillMaxSize()
            .padding(top = 104.dp, start = 24.dp, end = 24.dp),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {

            Text(
                stringResource(R.string.login),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold
            )

            OutlinedTextField(
                value = email,
                onValueChange = {
                    onEmailChanged(it)
                    isEmailValid = emailValidation(it)
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
                onValueChange = { onPasswordChanged(it) },
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

            TextButton(
                onClick = moveToForgetPass,
                modifier = modifier
                    .align(Alignment.End)
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.forgot_password)
                )
            }

            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        loggedIn()
                    } else {

                    }
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {
                Text(stringResource(R.string.login))
            }

            Text(
                stringResource(R.string.or),
                modifier = modifier
                    .align(CenterHorizontally)
                    .padding(top = 32.dp)
            )

            OutlinedButton(
                onClick = onLoginWithGoogle,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_google),
                    contentDescription = null,
                    modifier = modifier.padding(end = 8.dp)
                )
                Text(stringResource(R.string.login_with_google))
            }
        }
        Row(
            modifier = modifier
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(R.string.have_not_account),
                style = MaterialTheme.typography.bodyMedium,
            )
            TextButton(
                onClick = moveToRegister,
            ) {
                Text(
                    stringResource(R.string.register),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
