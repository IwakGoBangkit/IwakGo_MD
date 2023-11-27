package com.bangkit.fishery.ui.screen.change_email

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
import com.bangkit.fishery.R
import com.bangkit.fishery.ui.components.SectionText

@Composable
fun ChangeEmailScreen(
) {
    ChangeEmailContent()
}

@Composable
fun ChangeEmailContent(
    modifier: Modifier = Modifier
) {

    var email by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        SectionText(
            title = stringResource(R.string.change_email),
            modifier = modifier
        )

        OutlinedTextField(
            value = email,
            onValueChange = { newEmail -> email = newEmail },
            placeholder = { Text(text = stringResource(R.string.email_user)) },
            label = { Text(text = stringResource(R.string.email)) },
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