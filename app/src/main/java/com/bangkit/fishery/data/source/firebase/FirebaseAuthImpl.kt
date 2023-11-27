package com.bangkit.fishery.data.source.firebase

import android.content.Intent
import com.bangkit.fishery.ui.screen.authentication.model.UserData
import com.bangkit.fishery.ui.screen.authentication.model.UserModel
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

    override suspend fun loginWithIntent(intent: Intent): UserModel {
        val credential = onTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
        val task = Firebase.auth.signInWithCredential(googleCredential).await()
        val user = task.user
        return UserModel(
            data = user?.run {
                UserData(
                    userId = uid,
                    username = displayName,
                    photoUrl = photoUrl?.toString()
                )
            },
            errorMessage = null
        )
    }

    override suspend fun loginWithEmail(email: String, password: String): UserModel {
        val task = Firebase.auth.signInWithEmailAndPassword(email, password).await()
        val user = task.user
        return UserModel(
            data = user?.run {
                UserData(
                    userId = uid,
                    username = displayName,
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
        val task = Firebase.auth.createUserWithEmailAndPassword(email, password).await()
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
                    photoUrl = photoUrl?.toString()
                )
            },
            errorMessage = null
        )
    }

    override suspend fun getLoggedUser(): UserData? = Firebase.auth.currentUser?.run {
        UserData(
            userId = uid,
            username = displayName,
            photoUrl = photoUrl?.toString()
        )
    }



    override suspend fun signOut() {
        onTapClient.signOut().await()
        Firebase.auth.signOut()
    }
}