package com.levento.sfrapp.presentation.benefit_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levento.sfrapp.domain.model.Benefit
import com.levento.sfrapp.domain.repository.BenefitsRepository
import com.levento.sfrapp.domain.repository.UserRepository
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