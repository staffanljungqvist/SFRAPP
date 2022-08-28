package com.levento.sfrapp.domain.repository

import com.levento.sfrapp.domain.model.AuthResponse
import com.levento.sfrapp.domain.model.User
import kotlinx.coroutines.flow.Flow


interface UserRepository {

    suspend fun loginUser(email: String, password: String): Flow<AuthResponse<Boolean>>

    suspend fun checkLogin(): Boolean

    suspend fun getUserData(): User?

    suspend fun registerNewUser(email: String, password: String)

    val isUserAuthenticated: Boolean

    suspend fun logOut()

}