package com.bangkit.fishery_app.ui.screen.change_password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.ui.components.SectionText

@Composable
fun ChangePasswordScreen() {
    ChangePasswordContent()
}

@Composable
fun ChangePasswordContent(
    modifier: Modifier = Modifier
) {

    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        SectionText(title = stringResource(R.string.change_password))

        OutlinedTextField(
            value = password,
            onValueChange = { newPassword -> password = newPassword },
            label = { Text(text = stringResource(R.string.password)) },
            keyboardOptions = KeyboardOptions.Default,
            shape = RoundedCornerShape(24.dp),
            modifier = modifier.fillMaxWidth()
        )

        Spacer(modifier = modifier.size(16.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.save)
            )
        }
    }
}