package com.bangkit.fishery_app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ItemContentMethod(
    modifier: Modifier = Modifier,
    step: Int,
    title: String,
    description: String
) {
    Column(
        modifier = modifier
    ) {
        SectionText(title = "$step. $title")
        Text(
            text = description,
            textAlign = TextAlign.Justify
        )
    }
}