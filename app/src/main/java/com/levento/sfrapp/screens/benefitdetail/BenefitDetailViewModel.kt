package com.levento.sfrapp.screens.benefitdetail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levento.sfrapp.TAG
import com.levento.sfrapp.interfaces.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BenefitDetailViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn = _isLoggedIn

    fun checkLoginStatus() {
        viewModelScope.launch {
            _isLoggedIn.value = userRepository.checkLogin()
        }
    }
}