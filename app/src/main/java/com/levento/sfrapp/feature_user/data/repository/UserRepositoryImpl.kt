package com.levento.sfrapp.feature_user.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.levento.sfrapp.TAG
import com.levento.sfrapp.feature_user.domain.repository.UserRepository
import com.levento.sfrapp.feature_user.domain.model.AuthResponse
import com.levento.sfrapp.feature_user.domain.model.User
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : UserRepository {

    override val isUserAuthenticated = auth.currentUser != null

    override suspend fun loginUser(email: String, password: String) = flow {

        //TODO Ändra tillbaks till medskickade parametrar
        val testEmail = "user1@mail.com"
        val testPassword = "password"

        Log.d(TAG, "The user is trying to log in")
        try {
            emit(AuthResponse.Loading)
            auth.signInWithEmailAndPassword(testEmail,testPassword).await()
            emit(AuthResponse.Success(true))
        } catch (e: Exception) {
            emit(AuthResponse.Error(message = "Något gick fel"))
            Log.d("myDebug", "Kunde inte logga in" + e.message)
        }
    }


    override suspend fun checkLogin(): Boolean {
        return if (auth.currentUser!= null) {
            Log.d(TAG, "The user is logged in")
            true
        } else {
            Log.d(TAG, "The user is logged out")
            false
        }
    }

    //TODO använd network response
    override suspend fun getUserData(): User? {
        val userID = auth.currentUser?.uid ?: return null
        val response = db.collection("users").document(userID).get().await()
        val user = response.toObject<User>()
        return user
    }

    override suspend fun registerNewUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email!!, password!!).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                //     showUserMessage("Ny användare skapad")
            }
        }.addOnFailureListener { exception ->
            //    showUserMessage(exception.message.toString())
        }
    }

    override suspend fun logOut() {
        Log.d(TAG, "The user logge out")
        auth.signOut()
    }
}