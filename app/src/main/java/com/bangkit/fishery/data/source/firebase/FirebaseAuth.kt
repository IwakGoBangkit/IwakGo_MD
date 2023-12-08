package com.bangkit.fishery.data.source.firebase

import android.content.Intent
import com.bangkit.fishery.ui.screen.authentication.model.UserData
import com.bangkit.fishery.ui.screen.authentication.model.UserModel

interface FirebaseAuth {
    suspend fun loginWithIntent(intent: Intent): UserModel
    suspend fun loginWithEmail(email: String, password: String): UserModel
    suspend fun registerWithEmail(name: String, email: String, password: String): UserModel
    suspend fun getLoggedUser(): UserData?
    suspend fun signOut()
    suspend fun resetPassword(email: String): Boolean
}