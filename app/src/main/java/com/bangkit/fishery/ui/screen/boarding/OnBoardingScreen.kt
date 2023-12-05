package com.bangkit.fishery.ui.screen.boarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.fishery.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    moveToRegister: () -> Unit,
    moveToLogin: () -> Unit,
) {
    val systemUiController = rememberSystemUiController()

    val primary = MaterialTheme.colorScheme.primary
    val background = MaterialTheme.colorScheme.background

    DisposableEffect(Unit) {
        systemUiController.apply {
            setStatusBarColor(primary)
        }
        onDispose {
            systemUiController.apply {
                setStatusBarColor(background)
            }
        }
    }

    OnBoardingContent(
        modifier = modifier,
        moveToRegister = moveToRegister,
        moveToLogin = moveToLogin
    )
}

@Composable
fun OnBoardingContent(
    modifier: Modifier = Modifier,
    moveToRegister: () -> Unit,
    moveToLogin: () -> Unit,
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.primary)
    ) {
        Text(
            stringResource(R.string.welcome),
            fontSize = 24.sp,
            modifier = modifier.padding(start = 16.dp, top = 16.dp),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.background,
        )

        Text(
            stringResource(R.string.app_name),
            fontSize = 40.sp,
            modifier = modifier.padding(top = 8.dp, start = 16.dp),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.background,
        )

        Image(
            painter = painterResource(R.drawable.boarding),
            contentDescription = stringResource(R.string.boarding_desc),
            alignment = Alignment.Center,
            modifier = modifier
                .fillMaxWidth()
                .size(width = 320.dp, height = 304.dp)
        )

        Box(
            modifier = modifier
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .padding(top = 16.dp)
        ) {
            Column(
                modifier = modifier.padding(start = 24.dp, bottom = 16.dp, top = 32.dp, end = 24.dp)
            ) {
                Text(
                    stringResource(R.string.welcome1),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Justify
                )

                Text(
                    stringResource(R.string.welcome2),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Italic,
                    modifier = modifier.padding(top = 16.dp)
                )

                Text(
                    stringResource(R.string.welcome3),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = modifier.padding(top = 16.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = modifier
                        .padding(top = 16.dp)
                        .fillMaxSize()
                ) {
                    Button(
                        onClick = moveToRegister,
                        modifier = modifier.weight(1f)
                    ) {
                        Text(stringResource(R.string.new_account))
                    }

                    OutlinedButton(
                        onClick = moveToLogin,
                        modifier = modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = stringResource(R.string.login)
                        )
                        Text(
                            stringResource(R.string.login)
                        )
                    }
                }
            }
        }
    }
}
