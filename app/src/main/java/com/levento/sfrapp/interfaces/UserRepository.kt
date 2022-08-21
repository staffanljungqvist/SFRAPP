package com.levento.sfrapp.interfaces

import com.levento.sfrapp.models.Response
import com.levento.sfrapp.models.User
import kotlinx.coroutines.flow.Flow


interface UserRepository {

    suspend fun loginUser(email: String, password: String): Flow<Response<Boolean>>

    suspend fun checkLogin(): Boolean

    suspend fun getUserData(): User?

    suspend fun registerNewUser(email: String, password: String)

    val isUserAuthenticated: Boolean

    suspend fun logOut()

}