package com.bangkit.fishery_app.util

import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.ui.model.MethodPayment

object DummyMethodPayment {

    val methodPayment = listOf(
        MethodPayment(
            image = R.drawable.mandiri,
            name = "Bank Mandiri",
            number = "1265 9785 5463 0283",
        ),
        MethodPayment(
            image = R.drawable.bni,
            name = "Bank BNI",
            number = "5364 9234 1737 3164",
        ),
        MethodPayment(
            image = R.drawable.bri,
            name = "Bank BRI",
            number = "9249 7287 1334 7773",
        ),
        MethodPayment(
            image = R.drawable.bca,
            name = "Bank BCA",
            number = "1396 8319 9283 1296",
        ),
    )
}