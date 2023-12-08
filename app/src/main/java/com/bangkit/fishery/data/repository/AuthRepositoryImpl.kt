package com.bangkit.fishery.data.repository

import android.content.Intent
import com.bangkit.fishery.data.source.firebase.FirebaseAuth
import com.bangkit.fishery.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override fun loginWithIntent(intent: Intent) = flow {
        emit(Result.Loading())
        try {
            val result = firebaseAuth.loginWithIntent(intent)
            emit(Result.Success(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun loginWithEmail(email: String, password: String) = flow {
        emit(Result.Loading())
        try {
            val result = firebaseAuth.loginWithEmail(email, password)
            emit(Result.Success(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun registerWithEmail(
        name: String,
        email: String,
        password: String
    ) = flow {
        emit(Result.Loading())
        try {
            val result = firebaseAuth.registerWithEmail(name, email, password)
            emit(Result.Success(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun getLoggedUser() = flow {
        val user = firebaseAuth.getLoggedUser()
        emit(user)
    }.flowOn(Dispatchers.IO)

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }

    override fun resetPassword(email: String) = flow {
        emit(Result.Loading())
        try {
            val result = firebaseAuth.resetPassword(email)
            emit(Result.Success(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}