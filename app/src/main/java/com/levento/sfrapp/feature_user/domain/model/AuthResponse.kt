package com.levento.sfrapp.feature_user.domain.model

sealed class AuthResponse<out T> {

    object Loading: AuthResponse<Nothing>()

    data class Success<out T>(
        val data: T
    ): AuthResponse<T>()

    data class Error(
        val message: String
    ): AuthResponse<Nothing>()
}