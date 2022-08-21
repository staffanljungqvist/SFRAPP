package com.levento.sfrapp.feature_user.domain.repository

import com.levento.sfrapp.feature_user.domain.model.AuthResponse
import com.levento.sfrapp.feature_user.domain.model.User
import kotlinx.coroutines.flow.Flow


interface UserRepository {

    suspend fun loginUser(email: String, password: String): Flow<AuthResponse<Boolean>>

    suspend fun checkLogin(): Boolean

    suspend fun getUserData(): User?

    suspend fun registerNewUser(email: String, password: String)

    val isUserAuthenticated: Boolean

    suspend fun logOut()

}