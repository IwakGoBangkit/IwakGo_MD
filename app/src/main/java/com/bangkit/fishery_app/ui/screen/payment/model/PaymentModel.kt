package com.bangkit.fishery_app.ui.screen.payment.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentModel(
    val id: Long,
    val image: String,
    val title: String,
    val location: String,
    val phone: String,
    val price: Int,
    val count: Int? = 1,
    val address: String? = ""
): Parcelable