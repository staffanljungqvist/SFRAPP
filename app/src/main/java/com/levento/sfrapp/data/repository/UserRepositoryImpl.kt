package com.levento.sfrapp.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.levento.sfrapp.domain.repository.UserRepository
import com.levento.sfrapp.domain.model.AuthResponse
import com.levento.sfrapp.domain.model.User
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

        try {
            emit(AuthResponse.Loading)
            auth.signInWithEmailAndPassword(email,password).await()
            emit(AuthResponse.Success(true))
        } catch (e: Exception) {
            emit(AuthResponse.Error(message = e.message ?: "Something went wrong"))
        }
    }


    override suspend fun checkLogin(): Boolean {
        return auth.currentUser!= null
    }

    override suspend fun getUserData(): User? {
        val userID = auth.currentUser?.uid ?: return null
        val response = db.collection("users").document(userID).get().await()
        return response.toObject<User>()
    }

    override suspend fun registerNewUser(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    override suspend fun logOut() {
        auth.signOut()
    }
}