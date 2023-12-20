package com.bangkit.fishery_app.ui.screen.payment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.ui.components.PostInfo
import com.bangkit.fishery_app.ui.components.ProductCounter
import com.bangkit.fishery_app.ui.screen.payment.model.PaymentModel

@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier,
    content: PaymentModel?,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    navigateToMethodPayment: () -> Unit
) {
    content?.let {
        PaymentContent(
            image = it.image,
            title = it.title,
            location = it.location,
            phone = it.phone,
            price = it.price,
            id = it.id,
            count = it.count ?: 0,
            address = it.address ?: "",
            onProductCountChanged = onProductCountChanged,
            navigateToMethodPayment = navigateToMethodPayment,
            modifier = modifier
        )
    }
}

@Composable
fun PaymentContent(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    location: String,
    phone: String,
    price: Int,
    id: Long,
    count: Int,
    onProductCountChanged:(id: Long, count: Int) -> Unit,
    address: String,
    navigateToMethodPayment: () -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {

        Text(
            text = stringResource(R.string.product),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = image,
                contentDescription = image,
                modifier = modifier.size(160.dp)
            )

            Column(
                modifier = modifier
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(start = 8.dp)
                )

                PostInfo(
                    icon = Icons.Default.LocationOn, text = location
                )

                PostInfo(
                    icon = Icons.Default.Call, text = phone
                )

                PostInfo(
                    icon = Icons.Default.AttachMoney, text = price.toString()
                )
            }
        }

        ProductCounter(
            orderId = id,
            orderCount = count,
            onProductIncreased = {onProductCountChanged(id, count + 1)},
            onProductDecreased = {onProductCountChanged(id, count - 1)},
            modifier = modifier.align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = address,
            onValueChange = {  },
            label = { Text(stringResource(R.string.address)) },
            keyboardOptions = KeyboardOptions.Default,
            shape = RoundedCornerShape(24.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        Button(
            onClick = { navigateToMethodPayment() },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            Text(text = stringResource(R.string.choose_method_payment))
        }
    }
}