package com.bangkit.fishery_app.data.source.firebase

import android.content.Intent
import com.bangkit.fishery_app.ui.screen.authentication.model.UserData
import com.bangkit.fishery_app.ui.screen.authentication.model.UserModel
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthImpl @Inject constructor(
    private val onTapClient: SignInClient
) : FirebaseAuth {

    private val auth = Firebase.auth
    private val firestore = Firebase.firestore

    override suspend fun loginWithIntent(intent: Intent): UserModel {
        val credential = onTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
        val task = auth.signInWithCredential(googleCredential).await()
        val user = task.user

        firestore.collection("users").document(user?.uid.toString()).set(
            UserData(
                userId = user?.uid.toString(),
                username = user?.displayName,
                email = user?.email,
                photoUrl = user?.photoUrl.toString()
            )
        ).await()

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
        val task = auth.signInWithEmailAndPassword(email, password).await()
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
        val task = auth.createUserWithEmailAndPassword(email, password).await()
        val user = task.user
        val profileUpdate = userProfileChangeRequest {
            displayName = name
        }
        Firebase.auth.currentUser?.updateProfile(profileUpdate)?.await()

        val defaultPhoto = "https://i.ibb.co/NSv28Yh/pngegg-6.png"
        firestore.collection("users").document(auth.currentUser?.uid.toString()).set(
            UserData(
                userId = auth.currentUser?.uid.toString(),
                username = user?.displayName,
                email = email,
                photoUrl = defaultPhoto
            )
        ).await()
        return UserModel(
            data = user?.run {
                UserData(
                    userId = uid,
                    username = displayName,
                    email = email,
                    photoUrl = photoUrl.toString()
                )
            },
            errorMessage = null
        )
    }

    override suspend fun getLoggedUser(): UserData?  {
        val userId = auth.currentUser?.uid
        val result = firestore.collection("users")
            .document("$userId")
            .get()
            .await()
        return result.toObject<UserData>()
    }

    override suspend fun signOut() {
        onTapClient.signOut().await()
        Firebase.auth.signOut()
    }

    override suspend fun resetPassword(email: String): Boolean {
        auth.sendPasswordResetEmail(email).await()
        return true
    }
}