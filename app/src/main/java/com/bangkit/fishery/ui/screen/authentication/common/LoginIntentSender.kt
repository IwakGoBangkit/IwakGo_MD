package com.bangkit.fishery.ui.screen.authentication.common

import android.content.Context
import android.content.IntentSender
import com.bangkit.fishery.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.tasks.await

suspend fun loginIntentSender(context: Context): IntentSender? {
    return try {
        val onTapClient = Identity.getSignInClient(context)
        val result = onTapClient.beginSignIn(
            buildLoginRequest(context)
        ).await()
        result?.pendingIntent?.intentSender
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

private fun buildLoginRequest(context: Context): BeginSignInRequest {
    return BeginSignInRequest.Builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.web_client_id))
                .build()
        )
        .setAutoSelectEnabled(true)
        .build()
}