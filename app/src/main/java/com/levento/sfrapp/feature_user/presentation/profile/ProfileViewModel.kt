package com.levento.sfrapp.feature_user.presentation.profile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levento.sfrapp.TAG
import com.levento.sfrapp.feature_user.domain.model.AuthResponse
import com.levento.sfrapp.feature_user.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    //TODO lägg in i constructor

    private var _isLoggedIn by mutableStateOf(false)
    var isLoggedIn: Boolean = _isLoggedIn

    var signInResponse by mutableStateOf<AuthResponse<Boolean>>(AuthResponse.Success(false))
        private set

    suspend fun login(email: String? = "", password: String? = "") {
        //Todo ändra parametrar
      //  if (validateEmailAndPassword(email, password)) {
            userRepository.loginUser(email!!, password!!).collect { response ->
                if (response is AuthResponse.Success) Log.d(TAG, "Login succesful")
                signInResponse = response
    //        }
        }
    }

    suspend fun checkLoginStatus() {
        _isLoggedIn = userRepository.checkLogin()
    }


    suspend fun logOut() {
        userRepository.logOut()
    }

    private fun validateEmailAndPassword(email: String?, password: String?): Boolean {
        val isValid =
            email != null && email.isNotEmpty() && password != null && password.isNotEmpty()
        Log.d(TAG, isValid.toString())
        return isValid
    }
}