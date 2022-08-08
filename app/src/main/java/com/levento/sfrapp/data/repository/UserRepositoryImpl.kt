package com.levento.sfrapp.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.levento.sfrapp.interfaces.iUserRepository
import com.levento.sfrapp.models.User
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl : iUserRepository {

    val db = Firebase.firestore

    private val auth = FirebaseAuth.getInstance()

    override suspend fun loginUser(email:String,password:String): AuthResult? {
        return try{
            val data = auth.signInWithEmailAndPassword(email,password).await()
            return data
        }catch (e : Exception){
            return null
        }
    }

    override suspend fun checkLogin(): Boolean {
        return auth.currentUser != null
    }

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
        auth.signOut()
    }

    fun setUser() {

    }

}