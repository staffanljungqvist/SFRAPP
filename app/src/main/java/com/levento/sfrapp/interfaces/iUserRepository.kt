package com.levento.sfrapp.interfaces

import com.google.firebase.auth.AuthResult
import com.levento.sfrapp.models.User

interface iUserRepository {

    suspend fun loginUser(email: String, password: String): AuthResult?

    suspend fun checkLogin(): Boolean

    suspend fun getUserData(): User?

    suspend fun registerNewUser(email: String, password: String)

    suspend fun logOut()

}