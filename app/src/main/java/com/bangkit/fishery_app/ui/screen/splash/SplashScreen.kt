package com.bangkit.fishery_app.ui.screen.splash


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bangkit.fishery_app.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onTimeOut: (Boolean) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val isLoggedIn by viewModel.isLoggedIn
    val currentTimeOut by rememberUpdatedState(onTimeOut)

    LaunchedEffect(currentTimeOut) {
        delay(3000)
        onTimeOut(isLoggedIn)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.logo_fishery),
            contentDescription = null,
            modifier = Modifier
                .size(256.dp)
                .align(Alignment.Center)
        )
    }
}