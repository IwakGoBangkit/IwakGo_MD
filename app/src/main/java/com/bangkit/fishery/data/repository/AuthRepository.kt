package com.bangkit.fishery.data.repository

import android.content.Intent
import com.bangkit.fishery.ui.screen.authentication.model.UserData
import com.bangkit.fishery.ui.screen.authentication.model.UserModel
import com.bangkit.fishery.util.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginWithIntent(intent: Intent): Flow<Result<UserModel>>
    fun loginWithEmail(email: String, password: String): Flow<Result<UserModel>>
    fun registerWithEmail(name: String, email:String, password: String): Flow<Result<UserModel>>

    fun getLoggedUser(): Flow<UserData?>
    suspend fun signOut()
}