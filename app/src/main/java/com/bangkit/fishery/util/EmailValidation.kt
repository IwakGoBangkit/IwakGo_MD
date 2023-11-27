package com.bangkit.fishery.util

import android.util.Patterns

fun emailValidation(email: String) : Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}