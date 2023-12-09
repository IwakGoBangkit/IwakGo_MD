package com.bangkit.fishery_app.ui.screen.authentication.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.ui.components.LoadingDialog
import com.bangkit.fishery_app.util.emailValidation

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    moveToLogin: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RegisterContent(
        name = state.name,
        email = state.email,
        password = state.password,
        confirmationPassword = state.confirmationPassword,
        onNameChanged = { name ->
            viewModel.onEvent(RegisterEvent.OnNameChanged(name))
        },
        onEmailChanged = { email ->
            viewModel.onEvent(RegisterEvent.OnEmailChanged(email))
        },
        onPasswordChanged = { password ->
            viewModel.onEvent(RegisterEvent.OnPasswordChanged(password))
        },
        onConfirmationPasswordChanged = { confirmationPassword ->
            viewModel.onEvent(RegisterEvent.OnPasswordConfirmationChanged(confirmationPassword))
        },
        onRegister = {
            viewModel.onEvent(
                RegisterEvent.RegisterWithEmailPassword(
                    state.name,
                    state.email,
                    state.password
                )
            )
        },
        moveToLogin = moveToLogin,
        modifier = modifier
    )

    LaunchedEffect(state.registerSuccessful) {
        if (state.registerSuccessful) {
            moveToLogin()
            viewModel.onEvent(RegisterEvent.ResetState)
        }
    }

    if (state.isLoading) {
        LoadingDialog()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    name: String,
    email: String,
    password: String,
    confirmationPassword: String,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmationPasswordChanged: (String) -> Unit,
    onRegister: () -> Unit,
    moveToLogin: () -> Unit
) {

    var isEmailValid by remember {
        mutableStateOf(true)
    }

    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    var passwordConfirmationVisibility by remember {
        mutableStateOf(false)
    }

    var isPasswordMatching by remember {
        mutableStateOf(true)
    }

    val scrollState = rememberScrollState()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    scrolledContainerColor = MaterialTheme.colorScheme.background
                ),
                title = {
                    Text(
                        stringResource(R.string.register),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = modifier.padding(start = 8.dp)
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp)
                .verticalScroll(scrollState),
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
            ) {

                Text(
                    stringResource(R.string.fill_credential),
                    fontSize = 14.sp,
                    modifier = modifier
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { onNameChanged(it) },
                    label = { Text(stringResource(R.string.username)) },
                    keyboardOptions = KeyboardOptions.Default,
                    shape = RoundedCornerShape(24.dp),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email ->
                        onEmailChanged(email)
                        isEmailValid = emailValidation(email)
                    },
                    label = { Text(stringResource(R.string.email)) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email
                    ),
                    shape = RoundedCornerShape(24.dp),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                )

                if (!isEmailValid) {
                    Text(stringResource(R.string.invalid_email), color = Color.Red)
                }

                OutlinedTextField(
                    value = password,
                    onValueChange = { onPasswordChanged(it) },
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
                    shape = RoundedCornerShape(24.dp),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )

                OutlinedTextField(
                    value = confirmationPassword,
                    onValueChange = {
                        onConfirmationPasswordChanged(it)
                        isPasswordMatching = it == password
                    },
                    label = { Text(stringResource(R.string.confirmation_password)) },
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordConfirmationVisibility = !passwordConfirmationVisibility
                        }) {
                            Icon(
                                painter = if (passwordConfirmationVisibility) painterResource(R.drawable.ic_hide_password) else painterResource(
                                    R.drawable.ic_show_password
                                ),
                                contentDescription = null
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default,
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    shape = RoundedCornerShape(24.dp),
                    isError = !isPasswordMatching,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )

                if (!isPasswordMatching) {
                    Text(
                        text = stringResource(R.string.confirmation_password_text, Color.Red)
                    )
                }

                Text(
                    stringResource(R.string.agreement_terms_condition),
                    fontSize = 14.sp,
                    modifier = modifier.padding(top = 32.dp)
                )

                Button(
                    onClick = onRegister,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                ) {
                    Text(stringResource(R.string.register))
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = modifier
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    stringResource(R.string.have_account),
                    style = MaterialTheme.typography.bodyMedium,
                )
                TextButton(
                    onClick = moveToLogin,
                ) {
                    Text(
                        stringResource(R.string.login),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}


