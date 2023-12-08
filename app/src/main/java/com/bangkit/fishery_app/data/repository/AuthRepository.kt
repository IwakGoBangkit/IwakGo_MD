package com.bangkit.fishery_app.data.repository

import android.content.Intent
import com.bangkit.fishery_app.ui.screen.authentication.model.UserData
import com.bangkit.fishery_app.ui.screen.authentication.model.UserModel
import com.bangkit.fishery_app.util.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginWithIntent(intent: Intent): Flow<Result<UserModel>>
    fun loginWithEmail(email: String, password: String): Flow<Result<UserModel>>
    fun registerWithEmail(name: String, email:String, password: String): Flow<Result<UserModel>>

    fun getLoggedUser(): Flow<UserData?>
    suspend fun signOut()

    fun resetPassword(email: String): Flow<Result<Boolean>>
}