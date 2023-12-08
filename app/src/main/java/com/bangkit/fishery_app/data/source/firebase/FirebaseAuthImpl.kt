package com.bangkit.fishery_app.data.source.firebase

import android.content.Intent
import com.bangkit.fishery_app.ui.screen.authentication.model.UserData
import com.bangkit.fishery_app.ui.screen.authentication.model.UserModel
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthImpl @Inject constructor(
    private val onTapClient: SignInClient
) : FirebaseAuth {

    val firebase = Firebase.auth

    override suspend fun loginWithIntent(intent: Intent): UserModel {
        val credential = onTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
        val task = firebase.signInWithCredential(googleCredential).await()
        val user = task.user
        return UserModel(
            data = user?.run {
                UserData(
                    userId = uid,
                    username = displayName,
                    email = email,
                    photoUrl = photoUrl?.toString()
                )
            },
            errorMessage = null
        )
    }

    override suspend fun loginWithEmail(email: String, password: String): UserModel {
        val task = firebase.signInWithEmailAndPassword(email, password).await()
        val user = task.user
        return UserModel(
            data = user?.run {
                UserData(
                    userId = uid,
                    username = displayName,
                    email = email,
                    photoUrl = photoUrl?.toString()
                )
            },
            errorMessage = null
        )
    }

    override suspend fun registerWithEmail(
        name: String,
        email: String,
        password: String
    ): UserModel {
        val task = firebase.createUserWithEmailAndPassword(email, password).await()
        val user = task.user
        val profileUpdate = userProfileChangeRequest {
            displayName = name
        }
        Firebase.auth.currentUser?.updateProfile(profileUpdate)?.await()
        return UserModel(
            data = user?.run {
                UserData(
                    userId = uid,
                    username = displayName,
                    email = email,
                    photoUrl = photoUrl?.toString()
                )
            },
            errorMessage = null
        )
    }

    override suspend fun getLoggedUser(): UserData? = firebase.currentUser?.run {
        UserData(
            userId = uid,
            username = displayName,
            email = email,
            photoUrl = photoUrl?.toString()
        )
    }

    override suspend fun signOut() {
        onTapClient.signOut().await()
        Firebase.auth.signOut()
    }

    override suspend fun resetPassword(email: String): Boolean {
        firebase.sendPasswordResetEmail(email).await()
        return true
    }
}