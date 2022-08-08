package com.levento.sfrapp.interfaces

interface iAccountService {

    fun registerNewUser(email: String, password: String)

    suspend fun login(email: String, password: String): Boolean

    suspend fun checkIfLoggedIn(): Boolean
    
    suspend fun logOut()

}