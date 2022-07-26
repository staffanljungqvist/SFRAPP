package com.levento.sfrapp.presentation.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levento.sfrapp.domain.repository.BenefitsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    val repository: BenefitsRepository
) : ViewModel() {

    init {
       getUserData()
    }

    private fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            val benefits = repository.getAllBenefits()
        }
    }
}