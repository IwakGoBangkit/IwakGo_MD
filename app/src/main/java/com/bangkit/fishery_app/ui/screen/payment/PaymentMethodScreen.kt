package com.bangkit.fishery_app.ui.screen.payment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.ui.components.CardPayment
import com.bangkit.fishery_app.ui.components.SectionText
import com.bangkit.fishery_app.util.DummyMethodPayment.methodPayment

@Composable
fun PaymentMethodScreen() {
    PaymentMethodContent()
}

@Composable
fun PaymentMethodContent(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.padding(16.dp)
    ) {

        Text(
            text = stringResource(R.string.total),
            style = MaterialTheme.typography.titleLarge
        )

        SectionText(title = "Rp 5200,00")

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        ){
            Text(
                text = "Harga Produk",
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
            )
            Text(
                text = "4200",
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
            )
        }

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Pajak",
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
            )
            Text(
                text = "1000",
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
            )
        }

        Text(
            text = stringResource(R.string.information_payment),
            textAlign = TextAlign.Justify
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier.padding(top = 8.dp)
        ) {
            items(methodPayment) {
                CardPayment(image = it.image, name = it.name, number = it.number)
            }
        }

        Button(
            onClick = {  },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
            ) {
            Text(text = stringResource(R.string.pay))
        }
    }
}