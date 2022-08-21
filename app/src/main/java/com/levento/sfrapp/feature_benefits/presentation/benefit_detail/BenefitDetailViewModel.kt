package com.levento.sfrapp.feature_benefits.presentation.benefit_detail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levento.sfrapp.TAG
import com.levento.sfrapp.feature_benefits.domain.model.Benefit
import com.levento.sfrapp.feature_benefits.domain.repository.BenefitsRepository
import com.levento.sfrapp.feature_user.domain.model.User
import com.levento.sfrapp.feature_user.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BenefitDetailViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val benefitsRepository: BenefitsRepository
) : ViewModel() {

    private val _benefit = mutableStateOf(Benefit())
    val benefit = _benefit

    init {
        getBenefit()
    }

    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn = _isLoggedIn

    fun checkLoginStatus() {
        viewModelScope.launch {
            _isLoggedIn.value = userRepository.checkLogin()
        }
    }

    private fun getBenefit() {
        viewModelScope.launch {
            val response = benefitsRepository.getBenefit("5")
            response.data?.let {
                _benefit.value = it
            }
        }
    }
}