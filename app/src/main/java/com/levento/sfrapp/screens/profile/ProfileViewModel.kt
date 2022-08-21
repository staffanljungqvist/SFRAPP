package com.levento.sfrapp.screens.profile

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.levento.sfrapp.TAG
import com.levento.sfrapp.data.PlaceHolders
import com.levento.sfrapp.data.repository.UserRepositoryImpl
import com.levento.sfrapp.models.Response
import com.levento.sfrapp.models.User
import com.levento.sfrapp.screens.profile.LoginScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    //TODO lägg in i constructor
    val userRepository =
        UserRepositoryImpl(FirebaseAuth.getInstance(), FirebaseFirestore.getInstance())

    var signInResponse by mutableStateOf<Response<Boolean>>(Response.Success(false))
        private set

    suspend fun login(email: String? = "", password: String? = "") {
        //Todo ändra parametrar
      //  if (validateEmailAndPassword(email, password)) {
            userRepository.loginUser(email!!, password!!).collect { response ->
                if (response is Response.Success) Log.d(TAG, "Login succesful")
                signInResponse = response
    //        }
        }
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